package dev.danvega.notion.model.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.content.BlockContent;
import dev.danvega.notion.model.block.content.BulletedListItemContent;
import dev.danvega.notion.model.block.content.CodeContent;
import dev.danvega.notion.model.block.content.HeadingContent;
import dev.danvega.notion.model.block.content.ImageContent;
import dev.danvega.notion.model.block.content.NumberedListItemContent;
import dev.danvega.notion.model.block.content.ParagraphContent;
import dev.danvega.notion.model.block.content.ToDoContent;
import dev.danvega.notion.model.common.NotionObject;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Notion block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Block extends NotionObject {

    /**
     * The type of the block.
     */
    private String type;

    /**
     * The parent ID of the block.
     */
    @JsonProperty("parent_id")
    private String parentId;

    /**
     * Whether the block has children.
     */
    @JsonProperty("has_children")
    private Boolean hasChildren;

    /**
     * The content of the block, based on its type.
     * This is populated during deserialization based on the block type.
     */
    private BlockContent content;
    
    // Specific block type fields that map to the BlockContent field
    // These are used by Jackson for serialization/deserialization
    private ParagraphContent paragraph;
    private HeadingContent heading_1;
    private HeadingContent heading_2;
    private HeadingContent heading_3;
    private BulletedListItemContent bulleted_list_item;
    private NumberedListItemContent numbered_list_item;
    private ToDoContent to_do;
    private CodeContent code;
    private ImageContent image;
    
    /**
     * Default constructor.
     */
    public Block() {
    }
    
    /**
     * Constructor with fields.
     *
     * @param type the block type (string representation)
     * @param parentId the parent ID
     * @param hasChildren whether the block has children
     * @param content the content of the block
     */
    public Block(String type, String parentId, Boolean hasChildren, BlockContent content) {
        this.type = type;
        this.parentId = parentId;
        this.hasChildren = hasChildren;
        this.content = content;
        
        // Set the specific type field based on the block type
        if (type != null && content != null) {
            switch (BlockType.valueOf(type.toUpperCase())) {
                case PARAGRAPH:
                    this.paragraph = (ParagraphContent) content;
                    break;
                case HEADING_1:
                    this.heading_1 = (HeadingContent) content;
                    break;
                case HEADING_2:
                    this.heading_2 = (HeadingContent) content;
                    break;
                case HEADING_3:
                    this.heading_3 = (HeadingContent) content;
                    break;
                case BULLETED_LIST_ITEM:
                    this.bulleted_list_item = (BulletedListItemContent) content;
                    break;
                case NUMBERED_LIST_ITEM:
                    this.numbered_list_item = (NumberedListItemContent) content;
                    break;
                case TO_DO:
                    this.to_do = (ToDoContent) content;
                    break;
                case CODE:
                    this.code = (CodeContent) content;
                    break;
                case IMAGE:
                    this.image = (ImageContent) content;
                    break;
            }
        }
    }
    
    /**
     * Factory method to create a paragraph block.
     *
     * @param text the text content
     * @return a new paragraph block
     */
    public static Block paragraph(String text) {
        ParagraphContent content = ParagraphContent.of(text);
        return new Block("paragraph", null, false, content);
    }
    
    /**
     * Factory method to create a heading block.
     *
     * @param text the heading text
     * @param level the heading level (1, 2, or 3)
     * @return a new heading block
     */
    public static Block heading(String text, int level) {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Heading level must be 1, 2, or 3");
        }
        
        HeadingContent content = HeadingContent.of(text, level);
        String type = "heading_" + level;
        return new Block(type, null, false, content);
    }
    
    /**
     * Factory method to create a bulleted list item.
     *
     * @param text the text content
     * @return a new bulleted list item block
     */
    public static Block bulletedListItem(String text) {
        BulletedListItemContent content = BulletedListItemContent.of(text);
        return new Block("bulleted_list_item", null, false, content);
    }
    
    /**
     * Factory method to create a numbered list item.
     *
     * @param text the text content
     * @return a new numbered list item block
     */
    public static Block numberedListItem(String text) {
        NumberedListItemContent content = NumberedListItemContent.of(text);
        return new Block("numbered_list_item", null, false, content);
    }
    
    /**
     * Factory method to create a to-do item.
     *
     * @param text the text content
     * @param checked whether the item is checked
     * @return a new to-do block
     */
    public static Block toDo(String text, boolean checked) {
        ToDoContent content = ToDoContent.of(text, checked);
        return new Block("to_do", null, false, content);
    }
    
    /**
     * Factory method to create a code block.
     *
     * @param code the code content
     * @param language the programming language
     * @return a new code block
     */
    public static Block code(String code, String language) {
        CodeContent content = CodeContent.of(code, language);
        return new Block("code", null, false, content);
    }
    
    /**
     * Factory method to create an image block from an external URL.
     *
     * @param url the image URL
     * @return a new image block
     */
    public static Block imageFromUrl(String url) {
        ImageContent content = ImageContent.ofExternal(url);
        return new Block("image", null, false, content);
    }

    public String getType() {
        // If content is set, get the type from there to ensure consistency
        if (content != null) {
            return content.getType().getValue();
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public BlockContent getContent() {
        if (content != null) {
            return content;
        }
        
        // Return the content based on the block type
        if (type != null) {
            switch (type) {
                case "paragraph":
                    return paragraph;
                case "heading_1":
                    return heading_1;
                case "heading_2":
                    return heading_2;
                case "heading_3":
                    return heading_3;
                case "bulleted_list_item":
                    return bulleted_list_item;
                case "numbered_list_item":
                    return numbered_list_item;
                case "to_do":
                    return to_do;
                case "code":
                    return code;
                case "image":
                    return image;
            }
        }
        
        return null;
    }

    public void setContent(BlockContent content) {
        this.content = content;
        
        // Also set the specific type field for proper serialization
        if (content != null) {
            switch (content.getType()) {
                case PARAGRAPH:
                    this.paragraph = (ParagraphContent) content;
                    break;
                case HEADING_1:
                    this.heading_1 = (HeadingContent) content;
                    break;
                case HEADING_2:
                    this.heading_2 = (HeadingContent) content;
                    break;
                case HEADING_3:
                    this.heading_3 = (HeadingContent) content;
                    break;
                case BULLETED_LIST_ITEM:
                    this.bulleted_list_item = (BulletedListItemContent) content;
                    break;
                case NUMBERED_LIST_ITEM:
                    this.numbered_list_item = (NumberedListItemContent) content;
                    break;
                case TO_DO:
                    this.to_do = (ToDoContent) content;
                    break;
                case CODE:
                    this.code = (CodeContent) content;
                    break;
                case IMAGE:
                    this.image = (ImageContent) content;
                    break;
            }
            
            // Set the type string to match the content type
            this.type = content.getType().getValue();
        }
    }
    
    public ParagraphContent getParagraph() {
        return paragraph;
    }

    public void setParagraph(ParagraphContent paragraph) {
        this.paragraph = paragraph;
        this.content = paragraph;
        if (this.type == null) {
            this.type = "paragraph";
        }
    }

    public HeadingContent getHeading_1() {
        return heading_1;
    }

    public void setHeading_1(HeadingContent heading_1) {
        this.heading_1 = heading_1;
        this.content = heading_1;
        if (this.type == null) {
            this.type = "heading_1";
        }
    }

    public HeadingContent getHeading_2() {
        return heading_2;
    }

    public void setHeading_2(HeadingContent heading_2) {
        this.heading_2 = heading_2;
        this.content = heading_2;
        if (this.type == null) {
            this.type = "heading_2";
        }
    }

    public HeadingContent getHeading_3() {
        return heading_3;
    }

    public void setHeading_3(HeadingContent heading_3) {
        this.heading_3 = heading_3;
        this.content = heading_3;
        if (this.type == null) {
            this.type = "heading_3";
        }
    }

    public BulletedListItemContent getBulleted_list_item() {
        return bulleted_list_item;
    }

    public void setBulleted_list_item(BulletedListItemContent bulleted_list_item) {
        this.bulleted_list_item = bulleted_list_item;
        this.content = bulleted_list_item;
        if (this.type == null) {
            this.type = "bulleted_list_item";
        }
    }

    public NumberedListItemContent getNumbered_list_item() {
        return numbered_list_item;
    }

    public void setNumbered_list_item(NumberedListItemContent numbered_list_item) {
        this.numbered_list_item = numbered_list_item;
        this.content = numbered_list_item;
        if (this.type == null) {
            this.type = "numbered_list_item";
        }
    }

    public ToDoContent getTo_do() {
        return to_do;
    }

    public void setTo_do(ToDoContent to_do) {
        this.to_do = to_do;
        this.content = to_do;
        if (this.type == null) {
            this.type = "to_do";
        }
    }

    public CodeContent getCode() {
        return code;
    }

    public void setCode(CodeContent code) {
        this.code = code;
        this.content = code;
        if (this.type == null) {
            this.type = "code";
        }
    }

    public ImageContent getImage() {
        return image;
    }

    public void setImage(ImageContent image) {
        this.image = image;
        this.content = image;
        if (this.type == null) {
            this.type = "image";
        }
    }

    /**
     * Type-safe method to get content for paragraphs.
     *
     * @return the paragraph content, or null if this is not a paragraph block
     */
    public ParagraphContent getParagraphContent() {
        return type != null && type.equals("paragraph") ? paragraph : null;
    }
    
    /**
     * Type-safe method to get content for headings.
     *
     * @return the heading content, or null if this is not a heading block
     */
    public HeadingContent getHeadingContent() {
        if (type == null) return null;
        
        switch (type) {
            case "heading_1": return heading_1;
            case "heading_2": return heading_2;
            case "heading_3": return heading_3;
            default: return null;
        }
    }
    
    /**
     * Type-safe method to get rich text for any block type.
     *
     * @return the rich text, or null if this block doesn't have rich text
     */
    public List<RichText> getRichText() {
        BlockContent content = getContent();
        if (content instanceof ParagraphContent) {
            return ((ParagraphContent) content).getRichText();
        } else if (content instanceof HeadingContent) {
            return ((HeadingContent) content).getRichText();
        } else if (content instanceof BulletedListItemContent) {
            return ((BulletedListItemContent) content).getRichText();
        } else if (content instanceof NumberedListItemContent) {
            return ((NumberedListItemContent) content).getRichText();
        } else if (content instanceof ToDoContent) {
            return ((ToDoContent) content).getRichText();
        } else if (content instanceof CodeContent) {
            return ((CodeContent) content).getRichText();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Block block = (Block) o;
        return Objects.equals(type, block.type) &&
               Objects.equals(parentId, block.parentId) &&
               Objects.equals(hasChildren, block.hasChildren) &&
               Objects.equals(getContent(), block.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, parentId, hasChildren, getContent());
    }

    @Override
    public String toString() {
        return "Block{" +
               "type='" + type + '\'' +
               ", parentId='" + parentId + '\'' +
               ", hasChildren=" + hasChildren +
               ", content=" + getContent() +
               ", " + super.toString() +
               '}';
    }
}