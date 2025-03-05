package dev.danvega.notion.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * Base class for Notion API responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotionResponse {

    /**
     * The type of the object.
     */
    private String object;
    
    /**
     * Default constructor.
     */
    public NotionResponse() {
    }
    
    /**
     * Constructor with object.
     *
     * @param object the object type
     */
    public NotionResponse(String object) {
        this.object = object;
    }
    
    public String getObject() {
        return object;
    }
    
    public void setObject(String object) {
        this.object = object;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotionResponse that = (NotionResponse) o;
        return Objects.equals(object, that.object);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(object);
    }
    
    @Override
    public String toString() {
        return "NotionResponse{" +
               "object='" + object + '\'' +
               '}';
    }
}