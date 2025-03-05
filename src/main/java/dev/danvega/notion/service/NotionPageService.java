package dev.danvega.notion.service;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.page.Page;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for working with Notion pages.
 */
public class NotionPageService {

    private final NotionClient notionClient;

    /**
     * Constructs a new NotionPageService.
     *
     * @param notionClient the Notion client
     */
    public NotionPageService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    /**
     * Retrieves a page by ID.
     *
     * @param pageId the page ID
     * @return the page
     */
    public Page getPage(String pageId) {
        return notionClient.get("/pages/" + pageId, Page.class);
    }

    /**
     * Creates a new page.
     *
     * @param parent the parent of the page
     * @param properties the page properties
     * @return the created page
     */
    public Page createPage(Parent parent, Map<String, Object> properties) {
        return createPage(parent, properties, Collections.emptyList());
    }

    /**
     * Creates a new page with content.
     *
     * @param parent the parent of the page
     * @param properties the page properties
     * @param children the content blocks
     * @return the created page
     */
    public Page createPage(Parent parent, Map<String, Object> properties, List<Block> children) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("parent", parent);
        requestBody.put("properties", properties);

        if (children != null && !children.isEmpty()) {
            requestBody.put("children", children);
        }

        return notionClient.post("/pages", requestBody, Page.class);
    }

    /**
     * Updates a page.
     *
     * @param pageId the page ID
     * @param properties the updated properties
     * @return the updated page
     */
    public Page updatePage(String pageId, Map<String, Object> properties) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("properties", properties);

        return notionClient.patch("/pages/" + pageId, requestBody, Page.class);
    }

    /**
     * Archives a page.
     *
     * @param pageId the page ID
     * @return the archived page
     */
    public Page archivePage(String pageId) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("archived", true);

        return notionClient.patch("/pages/" + pageId, requestBody, Page.class);
    }

    /**
     * Unarchives a page.
     *
     * @param pageId the page ID
     * @return the unarchived page
     */
    public Page unarchivePage(String pageId) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("archived", false);

        return notionClient.patch("/pages/" + pageId, requestBody, Page.class);
    }
}