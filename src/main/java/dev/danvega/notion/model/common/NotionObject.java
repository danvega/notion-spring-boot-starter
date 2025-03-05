package dev.danvega.notion.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Base class for all Notion objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NotionObject {

    /**
     * The ID of the object.
     */
    private String id;

    /**
     * The type of the object.
     */
    @JsonProperty("object")
    private String objectType;

    /**
     * The time when the object was created.
     */
    private ZonedDateTime createdTime;

    /**
     * The time when the object was last updated.
     */
    private ZonedDateTime lastEditedTime;

    /**
     * Whether the object has been archived.
     */
    private Boolean archived;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public ZonedDateTime getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(ZonedDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotionObject that = (NotionObject) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(objectType, that.objectType) &&
               Objects.equals(createdTime, that.createdTime) &&
               Objects.equals(lastEditedTime, that.lastEditedTime) &&
               Objects.equals(archived, that.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectType, createdTime, lastEditedTime, archived);
    }

    @Override
    public String toString() {
        return "NotionObject{" +
               "id='" + id + '\'' +
               ", objectType='" + objectType + '\'' +
               ", createdTime=" + createdTime +
               ", lastEditedTime=" + lastEditedTime +
               ", archived=" + archived +
               '}';
    }
}