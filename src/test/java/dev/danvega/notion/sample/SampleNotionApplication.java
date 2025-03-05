package dev.danvega.notion.sample;

import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.common.RichText;
import dev.danvega.notion.model.database.DatabaseQuery;
import dev.danvega.notion.model.page.Page;
import dev.danvega.notion.model.response.PaginatedResponse;
import dev.danvega.notion.service.NotionDatabaseService;
import dev.danvega.notion.service.NotionPageService;
import dev.danvega.notion.service.NotionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Sample application demonstrating the usage of Notion Spring Boot Starter.
 * This can be used for manual testing and as a reference for users.
 */
@SpringBootApplication
@Profile("sample")
public class SampleNotionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleNotionApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(NotionService notionService,
                                 NotionPageService pageService,
                                 NotionDatabaseService databaseService) {
        return args -> {
            System.out.println("Starting Notion API demo...");
            
            // Get current user
            System.out.println("Getting current user...");
            var user = notionService.getCurrentUser();
            System.out.println("Current user: " + user);
            
            // A real application would use an actual database ID from your Notion workspace
            String databaseId = System.getenv("NOTION_DATABASE_ID");
            if (databaseId != null) {
                System.out.println("\nQuerying database: " + databaseId);
                
                // Build a simple query
                DatabaseQuery query = DatabaseQuery.builder()
                    .pageSize(5)
                    .build();
                
                // Query the database
                var result = databaseService.queryDatabase(databaseId, query);
                System.out.println("Found " + result.getResults().size() + " pages");
                
                // Display the results
                for (Page page : result.getResults()) {
                    System.out.println("- " + page.getId() + " (" + page.getUrl() + ")");
                }
            }
            
            // A real application would use an actual page ID from your Notion workspace
            String pageId = System.getenv("NOTION_PAGE_ID");
            if (pageId != null) {
                System.out.println("\nGetting page: " + pageId);
                Page page = pageService.getPage(pageId);
                System.out.println("Page title: " + page.getUrl());
            }
            
            System.out.println("\nDemo completed!");
        };
    }
}