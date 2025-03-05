package dev.danvega.notion.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exception thrown when there is an error with the Notion API.
 */
public class NotionApiException extends RuntimeException {

    private final HttpStatus statusCode;
    private final Map<String, Object> errorData;

    /**
     * Constructs a new NotionApiException with the specified detail message.
     *
     * @param message the detail message
     */
    public NotionApiException(String message) {
        super(message);
        this.statusCode = null;
        this.errorData = null;
    }

    /**
     * Constructs a new NotionApiException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public NotionApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = null;
        this.errorData = null;
    }

    /**
     * Constructs a new NotionApiException with the specified detail message, status code, and cause.
     *
     * @param message the detail message
     * @param statusCode the HTTP status code
     * @param cause the cause
     */
    public NotionApiException(String message, HttpStatus statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorData = null;
    }

    /**
     * Constructs a new NotionApiException with the specified detail message, status code, error data, and cause.
     *
     * @param message the detail message
     * @param statusCode the HTTP status code
     * @param errorData the error data from the API response
     * @param cause the cause
     */
    public NotionApiException(String message, HttpStatus statusCode, Map<String, Object> errorData, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorData = errorData;
    }

    /**
     * Gets the HTTP status code.
     *
     * @return the HTTP status code
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Gets the error data from the API response.
     *
     * @return the error data
     */
    public Map<String, Object> getErrorData() {
        return errorData;
    }
}