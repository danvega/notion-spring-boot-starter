package dev.danvega.notion.client;

import dev.danvega.notion.config.NotionProperties;
import dev.danvega.notion.exception.NotionApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Client for interacting with the Notion API.
 */
public class NotionClient {

    private static final Logger log = LoggerFactory.getLogger(NotionClient.class);
    
    private final NotionProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    /**
     * Constructs a new NotionClient.
     *
     * @param properties the Notion API properties
     * @param objectMapper the ObjectMapper for JSON handling
     * @param restClient the RestClient for HTTP requests
     */
    public NotionClient(NotionProperties properties, ObjectMapper objectMapper, RestClient restClient) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = restClient;
    }

    /**
     * Performs a GET request to the Notion API.
     *
     * @param endpoint the API endpoint
     * @param responseType the expected response type
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T get(String endpoint, Class<T> responseType) {
        return get(endpoint, responseType, null);
    }

    /**
     * Performs a GET request to the Notion API with query parameters.
     *
     * @param endpoint the API endpoint
     * @param responseType the expected response type
     * @param queryParams the query parameters
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T get(String endpoint, Class<T> responseType, Map<String, Object> queryParams) {
        String url = buildUrl(endpoint, queryParams);
        
        try {
            String responseBody = restClient.get()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, responseType);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Performs a GET request to the Notion API with a TypeReference for complex types.
     *
     * @param endpoint the API endpoint
     * @param typeReference the TypeReference for the response
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T get(String endpoint, TypeReference<T> typeReference) {
        return get(endpoint, typeReference, null);
    }

    /**
     * Performs a GET request to the Notion API with a TypeReference and query parameters.
     *
     * @param endpoint the API endpoint
     * @param typeReference the TypeReference for the response
     * @param queryParams the query parameters
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T get(String endpoint, TypeReference<T> typeReference, Map<String, Object> queryParams) {
        String url = buildUrl(endpoint, queryParams);

        try {
            String responseBody = restClient.get()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, typeReference);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Performs a POST request to the Notion API.
     *
     * @param endpoint the API endpoint
     * @param requestBody the request body
     * @param responseType the expected response type
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T post(String endpoint, Object requestBody, Class<T> responseType) {
        String url = buildUrl(endpoint, null);

        try {
            String responseBody = restClient.post()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .body(requestBody)
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, responseType);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Performs a POST request to the Notion API with a TypeReference for complex types.
     *
     * @param endpoint the API endpoint
     * @param requestBody the request body
     * @param typeReference the TypeReference for the response
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T post(String endpoint, Object requestBody, TypeReference<T> typeReference) {
        String url = buildUrl(endpoint, null);

        try {
            String responseBody = restClient.post()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .body(requestBody)
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, typeReference);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Performs a PATCH request to the Notion API.
     *
     * @param endpoint the API endpoint
     * @param requestBody the request body
     * @param responseType the expected response type
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T patch(String endpoint, Object requestBody, Class<T> responseType) {
        String url = buildUrl(endpoint, null);

        try {
            String responseBody = restClient.patch()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .body(requestBody)
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, responseType);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }
    
    /**
     * Performs a PATCH request to the Notion API with a TypeReference for complex types.
     *
     * @param endpoint the API endpoint
     * @param requestBody the request body
     * @param typeReference the TypeReference for the response
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T patch(String endpoint, Object requestBody, TypeReference<T> typeReference) {
        String url = buildUrl(endpoint, null);

        try {
            String responseBody = restClient.patch()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .body(requestBody)
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, typeReference);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Performs a DELETE request to the Notion API.
     *
     * @param endpoint the API endpoint
     * @param responseType the expected response type
     * @param <T> the type parameter for the response
     * @return the API response
     */
    public <T> T delete(String endpoint, Class<T> responseType) {
        String url = buildUrl(endpoint, null);

        try {
            String responseBody = restClient.delete()
                .uri(url)
                .headers(headers -> headers.putAll(createHeaders()))
                .retrieve()
                .body(String.class);
            
            return deserializeResponse(responseBody, responseType);
        } catch (ResponseStatusException e) {
            throw handleApiError(e);
        }
    }

    /**
     * Builds the full URL for a Notion API request.
     *
     * @param endpoint the API endpoint
     * @param queryParams the query parameters
     * @return the full URL
     */
    private String buildUrl(String endpoint, Map<String, Object> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + endpoint);

        if (queryParams != null) {
            queryParams.forEach((key, value) -> {
                if (value != null) {
                    builder.queryParam(key, value);
                }
            });
        }

        return builder.build().toUriString();
    }

    /**
     * Creates HTTP headers for a Notion API request.
     *
     * @return the HTTP headers
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + properties.getKey());
        headers.set("Notion-Version", properties.getVersion());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Deserializes a JSON response to the specified type.
     *
     * @param responseBody the JSON response body
     * @param responseType the expected response type
     * @param <T> the type parameter for the response
     * @return the deserialized response
     */
    private <T> T deserializeResponse(String responseBody, Class<T> responseType) {
        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize response: {}", e.getMessage());
            throw new NotionApiException("Failed to deserialize response", e);
        }
    }

    /**
     * Deserializes a JSON response to the specified TypeReference.
     *
     * @param responseBody the JSON response body
     * @param typeReference the TypeReference for the response
     * @param <T> the type parameter for the response
     * @return the deserialized response
     */
    private <T> T deserializeResponse(String responseBody, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(responseBody, typeReference);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize response: {}", e.getMessage());
            throw new NotionApiException("Failed to deserialize response", e);
        }
    }

    /**
     * Handles API errors.
     *
     * @param e the ResponseStatusException
     * @return a NotionApiException
     */
    private NotionApiException handleApiError(ResponseStatusException e) {
        try {
            // In Spring 6/Boot 3.2 with RestClient, ResponseStatusException doesn't provide 
            // response body directly, so we'll use the status and message
            HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
            String errorMessage = e.getReason() != null ? e.getReason() : "Notion API error";
            
            // Since we don't have direct access to the response body, we'll create minimal error data
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", errorMessage);
            errorData.put("status", status.value());
            
            return new NotionApiException(errorMessage, status, errorData, e);
        } catch (Exception ex) {
            return new NotionApiException("Notion API error: " + e.getMessage(), 
                HttpStatus.valueOf(e.getStatusCode().value()), e);
        }
    }
}