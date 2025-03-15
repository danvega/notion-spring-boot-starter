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

#### Basic Block Operations

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

#### Creating Blocks with Typed Models

The library provides strongly typed models for all Notion block types, making it easier to work with block content:

```java
// Using the BlockFactory for easy block creation
import static dev.danvega.notion.model.block.BlockFactory.*;

// Create blocks with different types
Block paragraphBlock = paragraph("This is a paragraph text");
Block headingBlock = heading("This is a heading", 1);
Block bulletPoint = bulletedListItem("This is a bullet point");
Block numberedItem = numberedListItem("This is a numbered item");
Block todoItem = toDo("Task to complete", false);
Block codeBlock = code("System.out.println(\"Hello World\");", "java");
Block image = imageFromUrl("https://example.com/image.jpg");

// Append multiple blocks at once
List<Block> createdBlocks = blockService.appendBlocks(
    "parent_id", 
    heading("Document Title", 1),
    paragraph("This is the first paragraph."),
    bulletedListItem("Important point 1"),
    bulletedListItem("Important point 2"),
    code("var x = 42;", "javascript")
);

// Convenience methods
Block heading = blockService.appendHeading("parent_id", "Section Title", 2);
Block paragraph = blockService.appendParagraph("parent_id", "Paragraph text");
Block todo = blockService.appendToDo("parent_id", "Buy groceries", false);

// Create a complete document
List<Block> document = blockService.createDocument(
    "parent_id",
    "Document Title",
    "Document Subtitle",
    "First paragraph with content.",
    "Second paragraph with more details.",
    "Conclusion paragraph."
);
```

#### Type-Safe Access to Block Content

```java
// Get a block from the API
Block block = blockService.getBlock("block_id");

// Type-safe access to content based on block type
if (block.getType().equals("paragraph")) {
    ParagraphContent content = block.getParagraphContent();
    List<RichText> richText = content.getRichText();
    System.out.println("Paragraph text: " + richText.get(0).getPlainText());
} else if (block.getType().startsWith("heading_")) {
    HeadingContent content = block.getHeadingContent();
    int level = content.getLevel();
    System.out.println("Heading level: " + level);
}

// Generic method to access rich text from any block type
List<RichText> text = block.getRichText();
if (text != null) {
    System.out.println("Block text: " + text.get(0).getPlainText());
}
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