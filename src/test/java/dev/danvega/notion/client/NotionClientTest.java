package dev.danvega.notion.client;

import dev.danvega.notion.config.NotionProperties;
import dev.danvega.notion.exception.NotionApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

/**
 * Basic tests for NotionClient.
 * 
 * Note: This test doesn't perform actual API calls or complex mocking
 * because of the challenges with mocking RestClient's fluent API.
 * In a real-world scenario, you would use WireMock or similar
 * for integration testing.
 */
@SpringBootTest(classes = dev.danvega.notion.integration.TestApplication.class)
@ActiveProfiles("test")
class NotionClientTest {

    private NotionProperties properties;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        properties = new NotionProperties();
        properties.setKey("test-key");
        properties.setVersion("2022-06-28");
        properties.setBaseUrl("https://api.notion.com/v1");
        
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateClientWithValidProperties() {
        // Simple test to ensure client can be created
        NotionClient client = new NotionClient(properties, objectMapper, RestClient.create());
        // No exceptions means success
    }
}