package org.springframework.social.bitbucket.api;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.bitbucket.api.impl.UTCDateDeserializer;

/**
 * Metadata about a file in a repository.
 * 
 * @author ericbottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketFileMetadata {

    @JsonProperty
    private String path;

    @JsonProperty
    private String revision;

    @JsonProperty("utctimestamp")
    @JsonDeserialize(using = UTCDateDeserializer.class)
    private Date timestamp;

    @JsonProperty
    private int size;

    public String getPath() {
        return path;
    }

    public String getRevision() {
        return revision;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getSize() {
        return size;
    }
}
