package dev.danvega.notion.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

/**
 * Represents a paginated response from the Notion API.
 *
 * @param <T> the type of items in the results
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedResponse<T> extends NotionResponse {

    /**
     * The list of results.
     */
    private List<T> results;

    /**
     * Whether there are more results available.
     */
    private boolean hasMore;

    /**
     * The cursor for the next page, if available.
     */
    private String nextCursor;
    
    /**
     * Default constructor.
     */
    public PaginatedResponse() {
    }
    
    /**
     * Constructor with fields.
     *
     * @param object the object type
     * @param results the list of results
     * @param hasMore whether there are more results
     * @param nextCursor the cursor for the next page
     */
    public PaginatedResponse(String object, List<T> results, boolean hasMore, String nextCursor) {
        super(object);
        this.results = results;
        this.hasMore = hasMore;
        this.nextCursor = nextCursor;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaginatedResponse<?> that = (PaginatedResponse<?>) o;
        return hasMore == that.hasMore &&
               Objects.equals(results, that.results) &&
               Objects.equals(nextCursor, that.nextCursor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), results, hasMore, nextCursor);
    }

    @Override
    public String toString() {
        return "PaginatedResponse{" +
               "results=" + results +
               ", hasMore=" + hasMore +
               ", nextCursor='" + nextCursor + '\'' +
               ", " + super.toString() +
               '}';
    }
}