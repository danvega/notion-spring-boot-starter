package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for a code block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeContent extends BlockContent {

    /**
     * Rich text elements.
     */
    @JsonProperty("rich_text")
    private List<RichText> richText;

    /**
     * Programming language for syntax highlighting.
     */
    private String language;

    /**
     * Optional caption for the code block.
     */
    private List<RichText> caption;

    /**
     * Default constructor.
     */
    public CodeContent() {
    }

    /**
     * Constructor with rich text and language.
     *
     * @param richText the rich text elements
     * @param language the programming language
     */
    public CodeContent(List<RichText> richText, String language) {
        this.richText = richText;
        this.language = language;
    }

    /**
     * Factory method to create code content from plain text.
     *
     * @param code the code text
     * @param language the programming language
     * @return the code content
     */
    public static CodeContent of(String code, String language) {
        List<RichText> richText = List.of(RichText.builder()
                .text(new RichText.TextContent(code))
                .build());
        return new CodeContent(richText, language);
    }

    @Override
    public BlockType getType() {
        return BlockType.CODE;
    }

    public List<RichText> getRichText() {
        return richText;
    }

    public void setRichText(List<RichText> richText) {
        this.richText = richText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<RichText> getCaption() {
        return caption;
    }

    public void setCaption(List<RichText> caption) {
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeContent that = (CodeContent) o;
        return Objects.equals(richText, that.richText) &&
                Objects.equals(language, that.language) &&
                Objects.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(richText, language, caption);
    }

    @Override
    public String toString() {
        return "CodeContent{" +
                "richText=" + richText +
                ", language='" + language + '\'' +
                ", caption=" + caption +
                '}';
    }
}