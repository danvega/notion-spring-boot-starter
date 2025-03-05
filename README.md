# Notion Spring Boot Starter

A Spring Boot starter for integrating with the Notion API. This library simplifies the process of interacting with Notion from your Spring Boot applications.

## Features

- Auto-configuration for Notion API integration
- Support for pages, databases, and blocks operations
- Easy querying and filtering of Notion databases
- Simple page creation and update operations
- Block content management

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>dev.danvega</groupId>
    <artifactId>notion-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Configuration

Add the following properties to your `application.properties` or `application.yml` file:

```properties
notion.api.key=your_notion_api_key
notion.api.version=2022-06-28
```

YAML:
```yaml
notion:
  api:
    key: your_notion_api_key
    version: 2022-06-28
```

## Usage

### Autowiring Services

```java
@Service
public class MyService {
    private final NotionService notionService;
    private final NotionPageService pageService;
    private final NotionDatabaseService databaseService;
    private final NotionBlockService blockService;

    public MyService(NotionService notionService,
                     NotionPageService pageService,
                     NotionDatabaseService databaseService,
                     NotionBlockService blockService) {
        this.notionService = notionService;
        this.pageService = pageService;
        this.databaseService = databaseService;
        this.blockService = blockService;
    }

    // Use the services here
}
```

### Working with Pages

```java
// Get a page
Page page = pageService.getPage("page_id");

// Create a page
Page newPage = pageService.createPage(parent, properties, children);

// Update a page
pageService.updatePage("page_id", properties);
```

### Working with Databases

```java
// Get a database
Database database = databaseService.getDatabase("database_id");

// Query a database
DatabaseQuery query = DatabaseQuery.builder()
    .filter(/* your filter */)
    .sorts(/* your sorts */)
    .build();

PaginatedResponse<Page> results = databaseService.queryDatabase("database_id", query);

// Create a database
Database newDatabase = databaseService.createDatabase(parent, title, properties);
```

### Working with Blocks

```java
// Get a block
Block block = blockService.getBlock("block_id");

// Get children of a block
PaginatedResponse<Block> children = blockService.getBlockChildren("block_id");

// Append blocks to a parent
List<Block> newBlocks = blockService.appendBlockChildren("parent_id", blocks);

// Update a block
Block updatedBlock = blockService.updateBlock("block_id", block);

// Delete a block
blockService.deleteBlock("block_id");
```

## Testing

### Running Integration Tests

The starter comes with integration tests that use WireMock to mock the Notion API. To run these tests:

```bash
mvn test
```

### Manual Testing

For manual testing with a real Notion API key:

1. Set the following environment variables:
   ```bash
   export NOTION_API_KEY=your_actual_notion_api_key
   export NOTION_DATABASE_ID=your_database_id  # Optional
   export NOTION_PAGE_ID=your_page_id  # Optional
   ```

2. Run the sample application:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=sample -Dspring-boot.run.main-class=com.example.notion.sample.SampleNotionApplication
   ```

## License

This project is licensed under the MIT License - see the LICENSE file for details.