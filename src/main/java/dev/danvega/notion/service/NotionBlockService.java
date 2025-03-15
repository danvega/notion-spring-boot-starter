package dev.danvega.notion.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.block.BlockFactory;
import dev.danvega.notion.model.common.RichText;
import dev.danvega.notion.model.response.PaginatedResponse;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    /**
     * Appends a paragraph block to a parent block.
     *
     * @param parentId the parent block ID
     * @param text the text content
     * @return the created block
     */
    public Block appendParagraph(String parentId, String text) {
        Block block = BlockFactory.paragraph(text);
        List<Block> result = appendBlockChildren(parentId, List.of(block)).getResults();
        return result.isEmpty() ? null : result.get(0);
    }
    
    /**
     * Appends a heading block to a parent block.
     *
     * @param parentId the parent block ID
     * @param text the text content
     * @param level the heading level (1, 2, or 3)
     * @return the created block
     */
    public Block appendHeading(String parentId, String text, int level) {
        Block block = BlockFactory.heading(text, level);
        List<Block> result = appendBlockChildren(parentId, List.of(block)).getResults();
        return result.isEmpty() ? null : result.get(0);
    }
    
    /**
     * Appends a to-do block to a parent block.
     *
     * @param parentId the parent block ID
     * @param text the text content
     * @param checked whether the to-do item is checked
     * @return the created block
     */
    public Block appendToDo(String parentId, String text, boolean checked) {
        Block block = BlockFactory.toDo(text, checked);
        List<Block> result = appendBlockChildren(parentId, List.of(block)).getResults();
        return result.isEmpty() ? null : result.get(0);
    }
    
    /**
     * Appends multiple blocks to a parent block.
     *
     * @param parentId the parent block ID
     * @param blocks the blocks to append
     * @return the list of created blocks
     */
    public List<Block> appendBlocks(String parentId, Block... blocks) {
        return appendBlockChildren(parentId, Arrays.asList(blocks)).getResults();
    }
    
    /**
     * Creates a simple document with headings and paragraphs.
     *
     * @param parentId the parent block ID
     * @param title the document title (heading 1)
     * @param subtitle the document subtitle (heading 2)
     * @param paragraphs the paragraphs to add
     * @return the list of created blocks
     */
    public List<Block> createDocument(String parentId, String title, String subtitle, String... paragraphs) {
        List<Block> blocks = new ArrayList<>();
        
        if (title != null) {
            blocks.add(BlockFactory.heading(title, 1));
        }
        
        if (subtitle != null) {
            blocks.add(BlockFactory.heading(subtitle, 2));
        }
        
        for (String paragraph : paragraphs) {
            blocks.add(BlockFactory.paragraph(paragraph));
        }
        
        return appendBlockChildren(parentId, blocks).getResults();
    }
}