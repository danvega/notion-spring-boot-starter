package dev.danvega.notion.model.page;

import dev.danvega.notion.model.common.NotionObject;
import dev.danvega.notion.model.common.Parent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a Notion page.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page extends NotionObject {

    /**
     * The parent of the page.
     */
    private Parent parent;

    /**
     * The properties of the page.
     */
    private Map<String, Object> properties;

    /**
     * The URL of the page.
     */
    private String url;
    
    /**
     * Default constructor.
     */
    public Page() {
    }
    
    /**
     * Constructor with fields.
     *
     * @param parent the parent of the page
     * @param properties the properties of the page
     * @param url the URL of the page
     */
    public Page(Parent parent, Map<String, Object> properties, String url) {
        this.parent = parent;
        this.properties = properties;
        this.url = url;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
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
        if (!super.equals(o)) return false;
        Page page = (Page) o;
        return Objects.equals(parent, page.parent) &&
               Objects.equals(properties, page.properties) &&
               Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parent, properties, url);
    }

    @Override
    public String toString() {
        return "Page{" +
               "parent=" + parent +
               ", properties=" + properties +
               ", url='" + url + '\'' +
               ", " + super.toString() +
               '}';
    }
}