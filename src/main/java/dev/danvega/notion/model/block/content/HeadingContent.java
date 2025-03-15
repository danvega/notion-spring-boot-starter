package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for heading blocks (h1, h2, h3).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadingContent extends BlockContent {

    /**
     * Rich text elements.
     */
    @JsonProperty("rich_text")
    private List<RichText> richText;

    /**
     * Color of the heading.
     */
    private String color;

    /**
     * Whether the heading is toggleable.
     */
    @JsonProperty("is_toggleable")
    private Boolean isToggleable;

    /**
     * The level of the heading (1, 2, or 3).
     */
    private int level;

    /**
     * Default constructor.
     */
    public HeadingContent() {
    }

    /**
     * Constructor with rich text and level.
     *
     * @param richText the rich text elements
     * @param level the heading level (1, 2, or 3)
     */
    public HeadingContent(List<RichText> richText, int level) {
        this.richText = richText;
        this.level = level;
    }

    /**
     * Factory method to create heading content from plain text.
     *
     * @param text the plain text
     * @param level the heading level (1, 2, or 3)
     * @return the heading content
     */
    public static HeadingContent of(String text, int level) {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Heading level must be 1, 2, or 3");
        }
        
        List<RichText> richText = List.of(RichText.builder()
                .text(new RichText.TextContent(text))
                .build());
        return new HeadingContent(richText, level);
    }

    @Override
    public BlockType getType() {
        switch (level) {
            case 1: return BlockType.HEADING_1;
            case 2: return BlockType.HEADING_2;
            case 3: return BlockType.HEADING_3;
            default: throw new IllegalStateException("Invalid heading level: " + level);
        }
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

    public Boolean getIsToggleable() {
        return isToggleable;
    }

    public void setIsToggleable(Boolean isToggleable) {
        this.isToggleable = isToggleable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Heading level must be 1, 2, or 3");
        }
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeadingContent that = (HeadingContent) o;
        return level == that.level &&
                Objects.equals(richText, that.richText) &&
                Objects.equals(color, that.color) &&
                Objects.equals(isToggleable, that.isToggleable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(richText, color, isToggleable, level);
    }

    @Override
    public String toString() {
        return "HeadingContent{" +
                "richText=" + richText +
                ", color='" + color + '\'' +
                ", isToggleable=" + isToggleable +
                ", level=" + level +
                '}';
    }
}