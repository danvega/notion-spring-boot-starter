package dev.danvega.notion.service;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.response.PaginatedResponse;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for working with Notion blocks.
 */
public class NotionBlockService {

    private final NotionClient notionClient;

    /**
     * Constructs a new NotionBlockService.
     *
     * @param notionClient the Notion client
     */
    public NotionBlockService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    /**
     * Retrieves a block by ID.
     *
     * @param blockId the block ID
     * @return the block
     */
    public Block getBlock(String blockId) {
        return notionClient.get("/blocks/" + blockId, Block.class);
    }

    /**
     * Updates a block.
     *
     * @param blockId the block ID
     * @param block the updated block content
     * @return the updated block
     */
    public Block updateBlock(String blockId, Block block) {
        return notionClient.patch("/blocks/" + blockId, block, Block.class);
    }

    /**
     * Deletes a block (marks it as archived).
     *
     * @param blockId the block ID
     * @return the deleted block
     */
    public Block deleteBlock(String blockId) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("archived", true);

        return notionClient.patch("/blocks/" + blockId, requestBody, Block.class);
    }

    /**
     * Retrieves a block's children.
     *
     * @param blockId the block ID
     * @return a paginated response with child blocks
     */
    public PaginatedResponse<Block> getBlockChildren(String blockId) {
        return notionClient.get(
            "/blocks/" + blockId + "/children",
            new TypeReference<PaginatedResponse<Block>>() {}
        );
    }

    /**
     * Appends children to a block.
     *
     * @param blockId the block ID
     * @param children the children to append
     * @return a paginated response with the appended blocks
     */
    public PaginatedResponse<Block> appendBlockChildren(String blockId, List<Block> children) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("children", children);

        return notionClient.patch(
            "/blocks/" + blockId + "/children",
            requestBody,
            new TypeReference<PaginatedResponse<Block>>() {}
        );
    }
}