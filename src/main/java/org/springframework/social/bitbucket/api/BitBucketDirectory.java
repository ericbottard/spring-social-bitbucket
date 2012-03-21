package org.springframework.social.bitbucket.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Metadata about the contents of a repository directory. Contains files,
 * directories and metadata about the selected directory.
 * 
 * @author ericbottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketDirectory {

    @JsonProperty
    private List<String> directories;

    @JsonProperty
    private List<BitBucketFileMetadata> files;

    @JsonProperty
    private String path;

    @JsonProperty
    private String node;

    public List<String> getDirectories() {
        return directories;
    }

    public List<BitBucketFileMetadata> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public String getNode() {
        return node;
    }

}
