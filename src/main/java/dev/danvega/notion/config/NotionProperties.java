package dev.danvega.notion.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Notion API.
 */
@ConfigurationProperties(prefix = "notion.api")
public class NotionProperties {

    /**
     * The API key (or integration token) for the Notion API.
     */
    private String key;

    /**
     * The Notion API version to use.
     */
    private String version = "2022-06-28";

    /**
     * The base URL for the Notion API.
     */
    private String baseUrl = "https://api.notion.com/v1";

    /**
     * Connection timeout in milliseconds.
     */
    private int connectionTimeout = 10000;

    /**
     * Read timeout in milliseconds.
     */
    private int readTimeout = 30000;

    /**
     * Write timeout in milliseconds.
     */
    private int writeTimeout = 30000;
    
    /**
     * Gets the API key.
     *
     * @return the API key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the API key.
     *
     * @param key the API key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the API version.
     *
     * @return the API version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the API version.
     *
     * @param version the API version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the base URL.
     *
     * @return the base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Sets the base URL.
     *
     * @param baseUrl the base URL
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Gets the connection timeout.
     *
     * @return the connection timeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout.
     *
     * @param connectionTimeout the connection timeout
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Gets the read timeout.
     *
     * @return the read timeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the read timeout.
     *
     * @param readTimeout the read timeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
     * Gets the write timeout.
     *
     * @return the write timeout
     */
    public int getWriteTimeout() {
        return writeTimeout;
    }

    /**
     * Sets the write timeout.
     *
     * @param writeTimeout the write timeout
     */
    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }
}