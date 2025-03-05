package dev.danvega.notion.service;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.common.RichText;
import dev.danvega.notion.model.database.Database;
import dev.danvega.notion.model.database.DatabaseQuery;
import dev.danvega.notion.model.page.Page;
import dev.danvega.notion.model.response.PaginatedResponse;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for working with Notion databases.
 */
public class NotionDatabaseService {

    private final NotionClient notionClient;

    /**
     * Constructs a new NotionDatabaseService.
     *
     * @param notionClient the Notion client
     */
    public NotionDatabaseService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    /**
     * Retrieves a database by ID.
     *
     * @param databaseId the database ID
     * @return the database
     */
    public Database getDatabase(String databaseId) {
        return notionClient.get("/databases/" + databaseId, Database.class);
    }

    /**
     * Creates a new database.
     *
     * @param parent the parent of the database
     * @param title the title of the database
     * @param properties the database properties
     * @return the created database
     */
    public Database createDatabase(Parent parent, List<RichText> title, Map<String, Object> properties) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("parent", parent);

        Map<String, Object> titleMap = new HashMap<>();
        titleMap.put("title", title);
        requestBody.put("title", title);

        requestBody.put("properties", properties);

        return notionClient.post("/databases", requestBody, Database.class);
    }

    /**
     * Updates a database.
     *
     * @param databaseId the database ID
     * @param title the updated title
     * @param properties the updated properties
     * @return the updated database
     */
    public Database updateDatabase(String databaseId, List<RichText> title, Map<String, Object> properties) {
        Map<String, Object> requestBody = new HashMap<>();

        if (title != null) {
            requestBody.put("title", title);
        }

        if (properties != null) {
            requestBody.put("properties", properties);
        }

        return notionClient.patch("/databases/" + databaseId, requestBody, Database.class);
    }

    /**
     * Queries a database.
     *
     * @param databaseId the database ID
     * @param query the query
     * @return a paginated response with pages
     */
    public PaginatedResponse<Page> queryDatabase(String databaseId, DatabaseQuery query) {
        return notionClient.post(
            "/databases/" + databaseId + "/query",
            query,
            new TypeReference<PaginatedResponse<Page>>() {}
        );
    }
}