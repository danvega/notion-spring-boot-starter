package dev.danvega.notion.autoconfigure;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.config.NotionProperties;
import dev.danvega.notion.service.NotionBlockService;
import dev.danvega.notion.service.NotionDatabaseService;
import dev.danvega.notion.service.NotionPageService;
import dev.danvega.notion.service.NotionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestClient;

/**
 * Auto-configuration for the Notion API integration.
 */
@Configuration
@EnableConfigurationProperties(NotionProperties.class)
@ConditionalOnProperty(prefix = "notion.api", name = "key")
public class NotionAutoConfiguration {

    /**
     * Creates a customized ObjectMapper for handling Notion API responses.
     *
     * @return the ObjectMapper bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper notionObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .build();
    }

    /**
     * Creates a RestClient for Notion API calls.
     *
     * @return the RestClient bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "notionRestClient")
    public RestClient notionRestClient() {
        return RestClient.create();
    }

    /**
     * Creates the Notion API client.
     *
     * @param properties the Notion API properties
     * @param objectMapper the ObjectMapper for JSON serialization/deserialization
     * @param notionRestClient the RestClient for making HTTP requests
     * @return the NotionClient bean
     */
    @Bean
    @ConditionalOnMissingBean
    public NotionClient notionClient(NotionProperties properties,
                                     ObjectMapper objectMapper,
                                     RestClient notionRestClient) {
        return new NotionClient(properties, objectMapper, notionRestClient);
    }

    /**
     * Creates the general Notion service.
     *
     * @param notionClient the Notion API client
     * @return the NotionService bean
     */
    @Bean
    @ConditionalOnMissingBean
    public NotionService notionService(NotionClient notionClient) {
        return new NotionService(notionClient);
    }

    /**
     * Creates the Notion page service.
     *
     * @param notionClient the Notion API client
     * @return the NotionPageService bean
     */
    @Bean
    @ConditionalOnMissingBean
    public NotionPageService notionPageService(NotionClient notionClient) {
        return new NotionPageService(notionClient);
    }

    /**
     * Creates the Notion database service.
     *
     * @param notionClient the Notion API client
     * @return the NotionDatabaseService bean
     */
    @Bean
    @ConditionalOnMissingBean
    public NotionDatabaseService notionDatabaseService(NotionClient notionClient) {
        return new NotionDatabaseService(notionClient);
    }

    /**
     * Creates the Notion block service.
     *
     * @param notionClient the Notion API client
     * @return the NotionBlockService bean
     */
    @Bean
    @ConditionalOnMissingBean
    public NotionBlockService notionBlockService(NotionClient notionClient) {
        return new NotionBlockService(notionClient);
    }
}