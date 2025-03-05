package dev.danvega.notion.model.block;

import dev.danvega.notion.model.common.NotionObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
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
    private String parentId;

    /**
     * Whether the block has children.
     */
    private Boolean hasChildren;

    /**
     * The content of the block, depending on its type.
     * This will be mapped from the specific type field in JSON
     * (e.g., "paragraph", "heading_1", etc.)
     */
    private Map<String, Object> content;
    
    // Additional fields for JSON deserialization of specific block types
    private Map<String, Object> paragraph;
    private Map<String, Object> heading_1;
    private Map<String, Object> heading_2;
    private Map<String, Object> heading_3;
    private Map<String, Object> bulleted_list_item;
    private Map<String, Object> numbered_list_item;
    private Map<String, Object> to_do;
    private Map<String, Object> code;
    private Map<String, Object> image;
    
    /**
     * Default constructor.
     */
    public Block() {
    }
    
    /**
     * Constructor with fields.
     *
     * @param type the block type
     * @param parentId the parent ID
     * @param hasChildren whether the block has children
     * @param content the content of the block
     */
    public Block(String type, String parentId, Boolean hasChildren, Map<String, Object> content) {
        this.type = type;
        this.parentId = parentId;
        this.hasChildren = hasChildren;
        this.content = content;
        
        // Set the specific type field based on the block type
        if (type != null) {
            switch (type) {
                case "paragraph":
                    this.paragraph = content;
                    break;
                case "heading_1":
                    this.heading_1 = content;
                    break;
                case "heading_2":
                    this.heading_2 = content;
                    break;
                case "heading_3":
                    this.heading_3 = content;
                    break;
                case "bulleted_list_item":
                    this.bulleted_list_item = content;
                    break;
                case "numbered_list_item":
                    this.numbered_list_item = content;
                    break;
                case "to_do":
                    this.to_do = content;
                    break;
                case "code":
                    this.code = content;
                    break;
                case "image":
                    this.image = content;
                    break;
            }
        }
    }

    public String getType() {
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

    public Map<String, Object> getContent() {
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

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
    
    public Map<String, Object> getParagraph() {
        return paragraph;
    }

    public void setParagraph(Map<String, Object> paragraph) {
        this.paragraph = paragraph;
        if (this.type == null) {
            this.type = "paragraph";
        }
    }

    public Map<String, Object> getHeading_1() {
        return heading_1;
    }

    public void setHeading_1(Map<String, Object> heading_1) {
        this.heading_1 = heading_1;
        if (this.type == null) {
            this.type = "heading_1";
        }
    }

    public Map<String, Object> getHeading_2() {
        return heading_2;
    }

    public void setHeading_2(Map<String, Object> heading_2) {
        this.heading_2 = heading_2;
        if (this.type == null) {
            this.type = "heading_2";
        }
    }

    public Map<String, Object> getHeading_3() {
        return heading_3;
    }

    public void setHeading_3(Map<String, Object> heading_3) {
        this.heading_3 = heading_3;
        if (this.type == null) {
            this.type = "heading_3";
        }
    }

    public Map<String, Object> getBulleted_list_item() {
        return bulleted_list_item;
    }

    public void setBulleted_list_item(Map<String, Object> bulleted_list_item) {
        this.bulleted_list_item = bulleted_list_item;
        if (this.type == null) {
            this.type = "bulleted_list_item";
        }
    }

    public Map<String, Object> getNumbered_list_item() {
        return numbered_list_item;
    }

    public void setNumbered_list_item(Map<String, Object> numbered_list_item) {
        this.numbered_list_item = numbered_list_item;
        if (this.type == null) {
            this.type = "numbered_list_item";
        }
    }

    public Map<String, Object> getTo_do() {
        return to_do;
    }

    public void setTo_do(Map<String, Object> to_do) {
        this.to_do = to_do;
        if (this.type == null) {
            this.type = "to_do";
        }
    }

    public Map<String, Object> getCode() {
        return code;
    }

    public void setCode(Map<String, Object> code) {
        this.code = code;
        if (this.type == null) {
            this.type = "code";
        }
    }

    public Map<String, Object> getImage() {
        return image;
    }

    public void setImage(Map<String, Object> image) {
        this.image = image;
        if (this.type == null) {
            this.type = "image";
        }
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