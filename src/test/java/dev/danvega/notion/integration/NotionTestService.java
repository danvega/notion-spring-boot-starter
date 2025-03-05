package dev.danvega.notion.integration;

import dev.danvega.notion.model.block.Block;
import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.common.RichText;
import dev.danvega.notion.model.database.Database;
import dev.danvega.notion.model.database.DatabaseQuery;
import dev.danvega.notion.model.page.Page;
import dev.danvega.notion.model.response.PaginatedResponse;
import dev.danvega.notion.service.NotionBlockService;
import dev.danvega.notion.service.NotionDatabaseService;
import dev.danvega.notion.service.NotionPageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service for testing Notion API interactions.
 */
@Service
public class NotionTestService {

    private final NotionPageService pageService;
    private final NotionDatabaseService databaseService;
    private final NotionBlockService blockService;

    public NotionTestService(NotionPageService pageService, 
                            NotionDatabaseService databaseService,
                            NotionBlockService blockService) {
        this.pageService = pageService;
        this.databaseService = databaseService;
        this.blockService = blockService;
    }

    public Page getPage(String pageId) {
        return pageService.getPage(pageId);
    }
    
    public Page createPage(Parent parent, Map<String, Object> properties) {
        return pageService.createPage(parent, properties);
    }
    
    public Database getDatabase(String databaseId) {
        return databaseService.getDatabase(databaseId);
    }
    
    public PaginatedResponse<Page> queryDatabase(String databaseId, DatabaseQuery query) {
        return databaseService.queryDatabase(databaseId, query);
    }
    
    public Database createDatabase(Parent parent, List<RichText> title, Map<String, Object> properties) {
        return databaseService.createDatabase(parent, title, properties);
    }
    
    public Block getBlock(String blockId) {
        return blockService.getBlock(blockId);
    }
    
    public PaginatedResponse<Block> getBlockChildren(String blockId) {
        return blockService.getBlockChildren(blockId);
    }
}