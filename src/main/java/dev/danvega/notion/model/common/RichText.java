package dev.danvega.notion.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents rich text content in Notion.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RichText {

    /**
     * The type of the rich text.
     */
    private String type;

    /**
     * Text content.
     */
    private TextContent text;

    /**
     * Annotation styles.
     */
    private Annotations annotations;

    /**
     * The plain text content.
     */
    private String plainText;

    /**
     * Any URL in the text.
     */
    private String href;

    /**
     * Default constructor.
     */
    public RichText() {
    }

    /**
     * Constructor with all fields.
     *
     * @param type the type of rich text
     * @param text the text content
     * @param annotations the annotation styles
     * @param plainText the plain text content
     * @param href the URL in the text
     */
    public RichText(String type, TextContent text, Annotations annotations, String plainText, String href) {
        this.type = type;
        this.text = text;
        this.annotations = annotations;
        this.plainText = plainText;
        this.href = href;
    }

    /**
     * Creates a simple text rich text object.
     *
     * @param content the plain text content
     * @return a new RichText
     */
    public static RichText of(String content) {
        TextContent textContent = new TextContent();
        textContent.setContent(content);
        return new RichTextBuilder()
                .type("text")
                .text(textContent)
                .plainText(content)
                .annotations(Annotations.getDefault())
                .build();
    }

    /**
     * Creates a rich text array from a string.
     *
     * @param content the plain text content
     * @return a list with a single RichText
     */
    public static List<RichText> listOf(String content) {
        return Collections.singletonList(of(content));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TextContent getText() {
        return text;
    }

    public void setText(TextContent text) {
        this.text = text;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RichText richText = (RichText) o;
        return Objects.equals(type, richText.type) &&
                Objects.equals(text, richText.text) &&
                Objects.equals(annotations, richText.annotations) &&
                Objects.equals(plainText, richText.plainText) &&
                Objects.equals(href, richText.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, text, annotations, plainText, href);
    }

    @Override
    public String toString() {
        return "RichText{" +
                "type='" + type + '\'' +
                ", text=" + text +
                ", annotations=" + annotations +
                ", plainText='" + plainText + '\'' +
                ", href='" + href + '\'' +
                '}';
    }

    /**
     * Builder for creating RichText instances.
     */
    public static class RichTextBuilder {
        private String type;
        private TextContent text;
        private Annotations annotations;
        private String plainText;
        private String href;

        public RichTextBuilder type(String type) {
            this.type = type;
            return this;
        }

        public RichTextBuilder text(TextContent text) {
            this.text = text;
            return this;
        }

        public RichTextBuilder annotations(Annotations annotations) {
            this.annotations = annotations;
            return this;
        }

        public RichTextBuilder plainText(String plainText) {
            this.plainText = plainText;
            return this;
        }

        public RichTextBuilder href(String href) {
            this.href = href;
            return this;
        }

        public RichText build() {
            return new RichText(type, text, annotations, plainText, href);
        }
    }

    /**
     * Represents text content in a rich text object.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TextContent {
        /**
         * The content of the text.
         */
        private String content;

        /**
         * Link URL, if the text is a link.
         */
        private Link link;

        /**
         * Default constructor.
         */
        public TextContent() {
        }

        /**
         * Constructor with all fields.
         *
         * @param content the content of the text
         * @param link the link URL
         */
        public TextContent(String content, Link link) {
            this.content = content;
            this.link = link;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Link getLink() {
            return link;
        }

        public void setLink(Link link) {
            this.link = link;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TextContent that = (TextContent) o;
            return Objects.equals(content, that.content) &&
                   Objects.equals(link, that.link);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content, link);
        }

        @Override
        public String toString() {
            return "TextContent{" +
                   "content='" + content + '\'' +
                   ", link=" + link +
                   '}';
        }
    }

    /**
     * Represents a link in a text content.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link {
        /**
         * The URL of the link.
         */
        private String url;

        /**
         * Default constructor.
         */
        public Link() {
        }

        /**
         * Constructor with URL.
         *
         * @param url the URL of the link
         */
        public Link(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Link link = (Link) o;
            return Objects.equals(url, link.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }

        @Override
        public String toString() {
            return "Link{" +
                   "url='" + url + '\'' +
                   '}';
        }
    }

    /**
     * Represents text annotations.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Annotations {
        /**
         * Whether the text is bold.
         */
        private Boolean bold;

        /**
         * Whether the text is italic.
         */
        private Boolean italic;

        /**
         * Whether the text is strikethrough.
         */
        private Boolean strikethrough;

        /**
         * Whether the text is underlined.
         */
        private Boolean underline;

        /**
         * Whether the text is code.
         */
        private Boolean code;

        /**
         * The color of the text.
         */
        private String color;

        /**
         * Default constructor.
         */
        public Annotations() {
        }

        /**
         * Constructor with all fields.
         *
         * @param bold whether text is bold
         * @param italic whether text is italic
         * @param strikethrough whether text has strikethrough
         * @param underline whether text is underlined
         * @param code whether text is code
         * @param color text color
         */
        public Annotations(Boolean bold, Boolean italic, Boolean strikethrough, 
                          Boolean underline, Boolean code, String color) {
            this.bold = bold;
            this.italic = italic;
            this.strikethrough = strikethrough;
            this.underline = underline;
            this.code = code;
            this.color = color;
        }

        /**
         * Gets default annotations.
         *
         * @return default annotations
         */
        public static Annotations getDefault() {
            return new AnnotationsBuilder()
                    .bold(false)
                    .italic(false)
                    .strikethrough(false)
                    .underline(false)
                    .code(false)
                    .color("default")
                    .build();
        }

        public Boolean getBold() {
            return bold;
        }

        public void setBold(Boolean bold) {
            this.bold = bold;
        }

        public Boolean getItalic() {
            return italic;
        }

        public void setItalic(Boolean italic) {
            this.italic = italic;
        }

        public Boolean getStrikethrough() {
            return strikethrough;
        }

        public void setStrikethrough(Boolean strikethrough) {
            this.strikethrough = strikethrough;
        }

        public Boolean getUnderline() {
            return underline;
        }

        public void setUnderline(Boolean underline) {
            this.underline = underline;
        }

        public Boolean getCode() {
            return code;
        }

        public void setCode(Boolean code) {
            this.code = code;
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
            Annotations that = (Annotations) o;
            return Objects.equals(bold, that.bold) &&
                   Objects.equals(italic, that.italic) &&
                   Objects.equals(strikethrough, that.strikethrough) &&
                   Objects.equals(underline, that.underline) &&
                   Objects.equals(code, that.code) &&
                   Objects.equals(color, that.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bold, italic, strikethrough, underline, code, color);
        }

        @Override
        public String toString() {
            return "Annotations{" +
                   "bold=" + bold +
                   ", italic=" + italic +
                   ", strikethrough=" + strikethrough +
                   ", underline=" + underline +
                   ", code=" + code +
                   ", color='" + color + '\'' +
                   '}';
        }

        /**
         * Builder for creating Annotations instances.
         */
        public static class AnnotationsBuilder {
            private Boolean bold;
            private Boolean italic;
            private Boolean strikethrough;
            private Boolean underline;
            private Boolean code;
            private String color;

            public AnnotationsBuilder bold(Boolean bold) {
                this.bold = bold;
                return this;
            }

            public AnnotationsBuilder italic(Boolean italic) {
                this.italic = italic;
                return this;
            }

            public AnnotationsBuilder strikethrough(Boolean strikethrough) {
                this.strikethrough = strikethrough;
                return this;
            }

            public AnnotationsBuilder underline(Boolean underline) {
                this.underline = underline;
                return this;
            }

            public AnnotationsBuilder code(Boolean code) {
                this.code = code;
                return this;
            }

            public AnnotationsBuilder color(String color) {
                this.color = color;
                return this;
            }

            public Annotations build() {
                return new Annotations(bold, italic, strikethrough, underline, code, color);
            }
        }
    }
}