package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for a paragraph block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParagraphContent extends BlockContent {

    /**
     * Rich text elements.
     */
    @JsonProperty("rich_text")
    private List<RichText> richText;

    /**
     * Color of the paragraph.
     */
    private String color;

    /**
     * Default constructor.
     */
    public ParagraphContent() {
    }

    /**
     * Constructor with rich text.
     *
     * @param richText the rich text elements
     */
    public ParagraphContent(List<RichText> richText) {
        this.richText = richText;
    }

    /**
     * Constructor with rich text and color.
     *
     * @param richText the rich text elements
     * @param color the color
     */
    public ParagraphContent(List<RichText> richText, String color) {
        this.richText = richText;
        this.color = color;
    }

    /**
     * Factory method to create paragraph content from plain text.
     *
     * @param text the plain text
     * @return the paragraph content
     */
    public static ParagraphContent of(String text) {
        List<RichText> richText = List.of(RichText.builder()
                .text(new RichText.TextContent(text))
                .build());
        return new ParagraphContent(richText);
    }

    @Override
    public BlockType getType() {
        return BlockType.PARAGRAPH;
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
        ParagraphContent that = (ParagraphContent) o;
        return Objects.equals(richText, that.richText) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(richText, color);
    }

    @Override
    public String toString() {
        return "ParagraphContent{" +
                "richText=" + richText +
                ", color='" + color + '\'' +
                '}';
    }
}