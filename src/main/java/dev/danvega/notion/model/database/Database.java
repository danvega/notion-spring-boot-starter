package dev.danvega.notion.model.database;

import dev.danvega.notion.model.common.NotionObject;
import dev.danvega.notion.model.common.Parent;
import dev.danvega.notion.model.common.RichText;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Notion database.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Database extends NotionObject {

    /**
     * The title of the database.
     */
    private List<RichText> title;

    /**
     * The properties (columns) of the database.
     */
    private Map<String, Object> properties;

    /**
     * The parent of the database.
     */
    private Parent parent;

    /**
     * The URL of the database.
     */
    private String url;
    
    /**
     * Default constructor.
     */
    public Database() {
    }
    
    /**
     * Constructor with fields.
     *
     * @param title the title of the database
     * @param properties the properties of the database
     * @param parent the parent of the database
     * @param url the URL of the database
     */
    public Database(List<RichText> title, Map<String, Object> properties, Parent parent, String url) {
        this.title = title;
        this.properties = properties;
        this.parent = parent;
        this.url = url;
    }

    public List<RichText> getTitle() {
        return title;
    }

    public void setTitle(List<RichText> title) {
        this.title = title;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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
        Database database = (Database) o;
        return Objects.equals(title, database.title) &&
               Objects.equals(properties, database.properties) &&
               Objects.equals(parent, database.parent) &&
               Objects.equals(url, database.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, properties, parent, url);
    }

    @Override
    public String toString() {
        return "Database{" +
               "title=" + title +
               ", properties=" + properties +
               ", parent=" + parent +
               ", url='" + url + '\'' +
               ", " + super.toString() +
               '}';
    }
}