package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.danvega.notion.model.block.BlockType;

/**
 * Base class for all Notion block content types.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ParagraphContent.class, name = "paragraph"),
    @JsonSubTypes.Type(value = HeadingContent.class, name = "heading_1"),
    @JsonSubTypes.Type(value = HeadingContent.class, name = "heading_2"),
    @JsonSubTypes.Type(value = HeadingContent.class, name = "heading_3"),
    @JsonSubTypes.Type(value = BulletedListItemContent.class, name = "bulleted_list_item"),
    @JsonSubTypes.Type(value = NumberedListItemContent.class, name = "numbered_list_item"),
    @JsonSubTypes.Type(value = ToDoContent.class, name = "to_do"),
    @JsonSubTypes.Type(value = CodeContent.class, name = "code"),
    @JsonSubTypes.Type(value = ImageContent.class, name = "image")
    // Add other block types as needed
})
public abstract class BlockContent {
    
    /**
     * Get the block type for this content.
     * 
     * @return the block type
     */
    public abstract BlockType getType();
}