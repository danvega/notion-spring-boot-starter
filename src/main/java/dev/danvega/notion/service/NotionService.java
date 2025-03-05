package dev.danvega.notion.service;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.common.RichText;
import dev.danvega.notion.model.database.Database;
import dev.danvega.notion.model.page.Page;
import dev.danvega.notion.model.response.PaginatedResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * General service for working with the Notion API.
 */
public class NotionService {

    private final NotionClient notionClient;

    /**
     * Constructs a new NotionService.
     *
     * @param notionClient the Notion client
     */
    public NotionService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    /**
     * Performs a search in Notion.
     *
     * @param query the search query
     * @return a paginated response with search results
     */
    public PaginatedResponse<Object> search(String query) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", query);

        return notionClient.post("/search", requestBody, new TypeReference<PaginatedResponse<Object>>() {});
    }

    /**
     * Performs a filtered search in Notion.
     *
     * @param query the search query
     * @param filter the filter for object types
     * @param sortDirection the direction to sort results
     * @param startCursor the pagination cursor
     * @param pageSize the number of results per page
     * @return a paginated response with search results
     */
    public PaginatedResponse<Object> search(String query, String filter,
                                           String sortDirection, String startCursor,
                                           Integer pageSize) {
        Map<String, Object> requestBody = new HashMap<>();

        if (query != null) {
            requestBody.put("query", query);
        }

        if (filter != null) {
            Map<String, String> filterMap = new HashMap<>();
            filterMap.put("value", filter);
            filterMap.put("property", "object");
            requestBody.put("filter", filterMap);
        }

        if (sortDirection != null) {
            Map<String, String> sort = new HashMap<>();
            sort.put("direction", sortDirection);
            requestBody.put("sort", sort);
        }

        if (startCursor != null) {
            requestBody.put("start_cursor", startCursor);
        }

        if (pageSize != null) {
            requestBody.put("page_size", pageSize);
        }

        return notionClient.post("/search", requestBody, new TypeReference<PaginatedResponse<Object>>() {});
    }

    /**
     * Retrieves a list of users.
     *
     * @return a paginated response with users
     */
    public PaginatedResponse<Object> listUsers() {
        return notionClient.get("/users", new TypeReference<PaginatedResponse<Object>>() {});
    }

    /**
     * Retrieves information about the current user.
     *
     * @return the current user
     */
    public Object getCurrentUser() {
        return notionClient.get("/users/me", Object.class);
    }

    /**
     * Retrieves information about a specific user.
     *
     * @param userId the user ID
     * @return the user
     */
    public Object getUser(String userId) {
        return notionClient.get("/users/" + userId, Object.class);
    }
}