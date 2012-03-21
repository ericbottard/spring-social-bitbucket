package org.springframework.social.bitbucket.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Content as well as metadata about a repository file.
 * 
 * @author ebottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketFile {

    @JsonProperty
    private String node;

    @JsonProperty
    private String path;

    @JsonProperty
    private String data;

    public String getNode() {
        return node;
    }

    /**
     * Returns the file path, relative to the root of the repository.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the actual content of the file, as a String.
     */
    public String getData() {
        return data;
    }
}
