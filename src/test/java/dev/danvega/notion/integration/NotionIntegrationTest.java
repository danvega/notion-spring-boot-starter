package dev.danvega.notion.integration;

import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.block.content.ParagraphContent;
import dev.danvega.notion.model.database.DatabaseQuery;
import dev.danvega.notion.model.page.Page;
import dev.danvega.notion.model.response.PaginatedResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for the Notion Spring Boot Starter.
 * Uses WireMock to mock the Notion API responses.
 */
@SpringBootTest(classes = TestApplication.class)
@TestPropertySource(properties = {
    "notion.api.key=test-api-key",
    "notion.api.baseUrl=http://localhost:8089/v1"
})
@ActiveProfiles("test")
public class NotionIntegrationTest {

    private WireMockServer wireMockServer;

    @Autowired
    private NotionTestService notionTestService;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(wireMockConfig()
            .port(8089)
            .withRootDirectory("temp_wm_logs"));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void shouldGetPage() {
        // Given
        String pageId = "test-page-id";
        String pageJson = "{\n" +
                "  \"object\": \"page\",\n" +
                "  \"id\": \"" + pageId + "\",\n" +
                "  \"created_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "  \"last_edited_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "  \"archived\": false,\n" +
                "  \"url\": \"https://www.notion.so/Test-Page-" + pageId + "\",\n" +
                "  \"properties\": {}\n" +
                "}";

        stubFor(get(urlEqualTo("/v1/pages/" + pageId))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(pageJson)));

        // When
        Page page = notionTestService.getPage(pageId);

        // Then
        assertThat(page).isNotNull();
        assertThat(page.getId()).isEqualTo(pageId);
        assertThat(page.getObjectType()).isEqualTo("page");
        assertThat(page.getUrl()).isEqualTo("https://www.notion.so/Test-Page-" + pageId);

        // Verify the request was made
        verify(getRequestedFor(urlEqualTo("/v1/pages/" + pageId))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28")));
    }

    @Test
    void shouldQueryDatabase() {
        // Given
        String databaseId = "test-database-id";
        String queryResponseJson = "{\n" +
                "  \"object\": \"list\",\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"object\": \"page\",\n" +
                "      \"id\": \"page-id-1\",\n" +
                "      \"created_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "      \"last_edited_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "      \"archived\": false,\n" +
                "      \"url\": \"https://www.notion.so/Test-Page-1\",\n" +
                "      \"properties\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"object\": \"page\",\n" +
                "      \"id\": \"page-id-2\",\n" +
                "      \"created_time\": \"2023-01-02T00:00:00.000Z\",\n" +
                "      \"last_edited_time\": \"2023-01-02T00:00:00.000Z\",\n" +
                "      \"archived\": false,\n" +
                "      \"url\": \"https://www.notion.so/Test-Page-2\",\n" +
                "      \"properties\": {}\n" +
                "    }\n" +
                "  ],\n" +
                "  \"has_more\": false,\n" +
                "  \"next_cursor\": null\n" +
                "}";

        stubFor(post(urlEqualTo("/v1/databases/" + databaseId + "/query"))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(queryResponseJson)));

        // When
        DatabaseQuery query = DatabaseQuery.builder().build();
        var response = notionTestService.queryDatabase(databaseId, query);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getResults()).hasSize(2);
        assertThat(response.getResults().get(0).getId()).isEqualTo("page-id-1");
        assertThat(response.getResults().get(1).getId()).isEqualTo("page-id-2");
        assertThat(response.isHasMore()).isFalse();

        // Verify the request was made
        verify(postRequestedFor(urlEqualTo("/v1/databases/" + databaseId + "/query"))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28")));
    }
    
    @Test
    void shouldGetBlock() {
        // Given
        String blockId = "test-block-id";
        String blockJson = "{\n" +
                "  \"object\": \"block\",\n" +
                "  \"id\": \"" + blockId + "\",\n" +
                "  \"created_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "  \"last_edited_time\": \"2023-01-01T00:00:00.000Z\",\n" +
                "  \"archived\": false,\n" +
                "  \"has_children\": false,\n" +
                "  \"type\": \"paragraph\",\n" +
                "  \"paragraph\": { \"type\": \"paragraph\", \"rich_text\": [] }\n" +
                "}";

        stubFor(get(urlEqualTo("/v1/blocks/" + blockId))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(blockJson)));

        // When
        Block block = notionTestService.getBlock(blockId);

        // Then
        assertThat(block).isNotNull();
        assertThat(block.getId()).isEqualTo(blockId);
        assertThat(block.getObjectType()).isEqualTo("block");
        assertThat(block.getType()).isEqualTo("paragraph");
        assertThat(block.getHasChildren()).isFalse();
        
        // Test the type-safe content
        assertThat(block.getContent()).isNotNull();
        assertThat(block.getContent()).isInstanceOf(ParagraphContent.class);
        assertThat(block.getParagraphContent()).isNotNull();

        // Verify the request was made
        verify(getRequestedFor(urlEqualTo("/v1/blocks/" + blockId))
                .withHeader("Authorization", equalTo("Bearer test-api-key"))
                .withHeader("Notion-Version", equalTo("2022-06-28")));
    }
}