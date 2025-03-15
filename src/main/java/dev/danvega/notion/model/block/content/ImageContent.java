package dev.danvega.notion.model.block.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.danvega.notion.model.block.BlockType;
import dev.danvega.notion.model.common.RichText;

import java.util.List;
import java.util.Objects;

/**
 * Content for an image block.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageContent extends BlockContent {

    /**
     * File information, used when the image is hosted by Notion.
     */
    private FileObject file;

    /**
     * External information, used when the image is externally hosted.
     */
    private ExternalObject external;

    /**
     * Caption for the image.
     */
    private List<RichText> caption;

    /**
     * Default constructor.
     */
    public ImageContent() {
    }

    /**
     * Factory method to create an image content with a Notion-hosted file.
     *
     * @param url the file URL
     * @param expiryTime the expiry time
     * @return the image content
     */
    public static ImageContent ofFile(String url, String expiryTime) {
        ImageContent content = new ImageContent();
        FileObject file = new FileObject();
        file.setUrl(url);
        file.setExpiryTime(expiryTime);
        content.setFile(file);
        return content;
    }

    /**
     * Factory method to create an image content with an external URL.
     *
     * @param url the external URL
     * @return the image content
     */
    public static ImageContent ofExternal(String url) {
        ImageContent content = new ImageContent();
        ExternalObject external = new ExternalObject();
        external.setUrl(url);
        content.setExternal(external);
        return content;
    }

    @Override
    public BlockType getType() {
        return BlockType.IMAGE;
    }

    public FileObject getFile() {
        return file;
    }

    public void setFile(FileObject file) {
        this.file = file;
    }

    public ExternalObject getExternal() {
        return external;
    }

    public void setExternal(ExternalObject external) {
        this.external = external;
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
        ImageContent that = (ImageContent) o;
        return Objects.equals(file, that.file) &&
                Objects.equals(external, that.external) &&
                Objects.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, external, caption);
    }

    @Override
    public String toString() {
        return "ImageContent{" +
                "file=" + file +
                ", external=" + external +
                ", caption=" + caption +
                '}';
    }

    /**
     * Represents a file hosted by Notion.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FileObject {
        private String url;
        
        @JsonProperty("expiry_time")
        private String expiryTime;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(String expiryTime) {
            this.expiryTime = expiryTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileObject that = (FileObject) o;
            return Objects.equals(url, that.url) &&
                    Objects.equals(expiryTime, that.expiryTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, expiryTime);
        }

        @Override
        public String toString() {
            return "FileObject{" +
                    "url='" + url + '\'' +
                    ", expiryTime='" + expiryTime + '\'' +
                    '}';
        }
    }

    /**
     * Represents an externally hosted object.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExternalObject {
        private String url;

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
            ExternalObject that = (ExternalObject) o;
            return Objects.equals(url, that.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }

        @Override
        public String toString() {
            return "ExternalObject{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }
}