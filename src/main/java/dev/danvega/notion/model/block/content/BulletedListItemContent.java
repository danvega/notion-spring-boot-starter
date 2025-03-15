package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for a bulleted list item block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulletedListItemContent extends BlockContent {

    /**
     * Rich text elements.
     */
    @JsonProperty("rich_text")
    private List<RichText> richText;

    /**
     * Color of the list item.
     */
    private String color;

    /**
     * Default constructor.
     */
    public BulletedListItemContent() {
    }

    /**
     * Constructor with rich text.
     *
     * @param richText the rich text elements
     */
    public BulletedListItemContent(List<RichText> richText) {
        this.richText = richText;
    }

    /**
     * Factory method to create bulleted list item content from plain text.
     *
     * @param text the plain text
     * @return the bulleted list item content
     */
    public static BulletedListItemContent of(String text) {
        List<RichText> richText = List.of(RichText.builder()
                .text(new RichText.TextContent(text))
                .build());
        return new BulletedListItemContent(richText);
    }

    @Override
    public BlockType getType() {
        return BlockType.BULLETED_LIST_ITEM;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletedListItemContent that = (BulletedListItemContent) o;
        return Objects.equals(richText, that.richText) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(richText, color);
    }

    @Override
    public String toString() {
        return "BulletedListItemContent{" +
                "richText=" + richText +
                ", color='" + color + '\'' +
                '}';
    }
}