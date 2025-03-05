package dev.danvega.notion.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Represents a parent object in Notion.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parent {

    /**
     * The type of the parent.
     */
    private String type;

    /**
     * The ID of the database parent.
     */
    @JsonProperty("database_id")
    private String databaseId;

    /**
     * The ID of the page parent.
     */
    @JsonProperty("page_id")
    private String pageId;

    /**
     * The ID of the block parent.
     */
    @JsonProperty("block_id")
    private String blockId;

    /**
     * Whether the parent is a workspace.
     */
    @JsonProperty("workspace")
    private Boolean workspace;

    /**
     * Default constructor.
     */
    public Parent() {
    }

    /**
     * Constructor with all fields.
     *
     * @param type the type of parent
     * @param databaseId the database ID
     * @param pageId the page ID
     * @param blockId the block ID
     * @param workspace whether the parent is a workspace
     */
    public Parent(String type, String databaseId, String pageId, String blockId, Boolean workspace) {
        this.type = type;
        this.databaseId = databaseId;
        this.pageId = pageId;
        this.blockId = blockId;
        this.workspace = workspace;
    }

    /**
     * Creates a database parent.
     *
     * @param databaseId the database ID
     * @return a new Parent
     */
    public static Parent database(String databaseId) {
        return new ParentBuilder()
                .type("database_id")
                .databaseId(databaseId)
                .build();
    }

    /**
     * Creates a page parent.
     *
     * @param pageId the page ID
     * @return a new Parent
     */
    public static Parent page(String pageId) {
        return new ParentBuilder()
                .type("page_id")
                .pageId(pageId)
                .build();
    }

    /**
     * Creates a block parent.
     *
     * @param blockId the block ID
     * @return a new Parent
     */
    public static Parent block(String blockId) {
        return new ParentBuilder()
                .type("block_id")
                .blockId(blockId)
                .build();
    }

    /**
     * Creates a workspace parent.
     *
     * @return a new Parent
     */
    public static Parent workspace() {
        return new ParentBuilder()
                .type("workspace")
                .workspace(true)
                .build();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public Boolean getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Boolean workspace) {
        this.workspace = workspace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return Objects.equals(type, parent.type) &&
               Objects.equals(databaseId, parent.databaseId) &&
               Objects.equals(pageId, parent.pageId) &&
               Objects.equals(blockId, parent.blockId) &&
               Objects.equals(workspace, parent.workspace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, databaseId, pageId, blockId, workspace);
    }

    @Override
    public String toString() {
        return "Parent{" +
               "type='" + type + '\'' +
               ", databaseId='" + databaseId + '\'' +
               ", pageId='" + pageId + '\'' +
               ", blockId='" + blockId + '\'' +
               ", workspace=" + workspace +
               '}';
    }

    /**
     * Builder for creating Parent instances.
     */
    public static class ParentBuilder {
        private String type;
        private String databaseId;
        private String pageId;
        private String blockId;
        private Boolean workspace;

        public ParentBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ParentBuilder databaseId(String databaseId) {
            this.databaseId = databaseId;
            return this;
        }

        public ParentBuilder pageId(String pageId) {
            this.pageId = pageId;
            return this;
        }

        public ParentBuilder blockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public ParentBuilder workspace(Boolean workspace) {
            this.workspace = workspace;
            return this;
        }

        public Parent build() {
            return new Parent(type, databaseId, pageId, blockId, workspace);
        }
    }
}