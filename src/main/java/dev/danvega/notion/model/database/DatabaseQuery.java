package dev.danvega.notion.model.database;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a query for a Notion database.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseQuery {

    /**
     * Filter conditions.
     */
    private Map<String, Object> filter;

    /**
     * Sort conditions.
     */
    private List<Map<String, Object>> sorts;

    /**
     * The pagination cursor.
     */
    private String startCursor;

    /**
     * The number of results per page.
     */
    private Integer pageSize;
    
    /**
     * Default constructor.
     */
    public DatabaseQuery() {
    }
    
    /**
     * Constructor with all fields.
     *
     * @param filter filter conditions
     * @param sorts sort conditions
     * @param startCursor pagination cursor
     * @param pageSize number of results per page
     */
    public DatabaseQuery(Map<String, Object> filter, List<Map<String, Object>> sorts, 
                         String startCursor, Integer pageSize) {
        this.filter = filter;
        this.sorts = sorts;
        this.startCursor = startCursor;
        this.pageSize = pageSize;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public List<Map<String, Object>> getSorts() {
        return sorts;
    }

    public void setSorts(List<Map<String, Object>> sorts) {
        this.sorts = sorts;
    }

    public String getStartCursor() {
        return startCursor;
    }

    public void setStartCursor(String startCursor) {
        this.startCursor = startCursor;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseQuery that = (DatabaseQuery) o;
        return Objects.equals(filter, that.filter) &&
               Objects.equals(sorts, that.sorts) &&
               Objects.equals(startCursor, that.startCursor) &&
               Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, sorts, startCursor, pageSize);
    }

    @Override
    public String toString() {
        return "DatabaseQuery{" +
               "filter=" + filter +
               ", sorts=" + sorts +
               ", startCursor='" + startCursor + '\'' +
               ", pageSize=" + pageSize +
               '}';
    }
    
    /**
     * Builder for creating DatabaseQuery instances.
     */
    public static class DatabaseQueryBuilder {
        private Map<String, Object> filter;
        private List<Map<String, Object>> sorts;
        private String startCursor;
        private Integer pageSize;

        public DatabaseQueryBuilder filter(Map<String, Object> filter) {
            this.filter = filter;
            return this;
        }

        public DatabaseQueryBuilder sorts(List<Map<String, Object>> sorts) {
            this.sorts = sorts;
            return this;
        }

        public DatabaseQueryBuilder startCursor(String startCursor) {
            this.startCursor = startCursor;
            return this;
        }

        public DatabaseQueryBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public DatabaseQuery build() {
            return new DatabaseQuery(filter, sorts, startCursor, pageSize);
        }
    }
    
    /**
     * Creates a new builder for DatabaseQuery.
     *
     * @return a new builder
     */
    public static DatabaseQueryBuilder builder() {
        return new DatabaseQueryBuilder();
    }
}