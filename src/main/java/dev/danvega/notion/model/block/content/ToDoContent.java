package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for a to-do block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDoContent extends BlockContent {

    /**
     * Rich text elements.
     */
    @JsonProperty("rich_text")
    private List<RichText> richText;

    /**
     * Color of the to-do item.
     */
    private String color;

    /**
     * Whether the to-do item is checked.
     */
    private Boolean checked;

    /**
     * Default constructor.
     */
    public ToDoContent() {
    }

    /**
     * Constructor with rich text and checked status.
     *
     * @param richText the rich text elements
     * @param checked whether the to-do item is checked
     */
    public ToDoContent(List<RichText> richText, Boolean checked) {
        this.richText = richText;
        this.checked = checked;
    }

    /**
     * Factory method to create to-do content from plain text.
     *
     * @param text the plain text
     * @param checked whether the to-do item is checked
     * @return the to-do content
     */
    public static ToDoContent of(String text, boolean checked) {
        List<RichText> richText = List.of(RichText.builder()
                .text(new RichText.TextContent(text))
                .build());
        return new ToDoContent(richText, checked);
    }

    @Override
    public BlockType getType() {
        return BlockType.TO_DO;
    }

    public List<RichText> getRichText() {
        return richText;
    }

    public void setRichText(List<RichText> richText) {
        this.richText = richText;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoContent that = (ToDoContent) o;
        return Objects.equals(richText, that.richText) &&
                Objects.equals(color, that.color) &&
                Objects.equals(checked, that.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(richText, color, checked);
    }

    @Override
    public String toString() {
        return "ToDoContent{" +
                "richText=" + richText +
                ", color='" + color + '\'' +
                ", checked=" + checked +
                '}';
    }
}