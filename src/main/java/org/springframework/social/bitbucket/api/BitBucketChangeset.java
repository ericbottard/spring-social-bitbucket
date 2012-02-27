package org.springframework.social.bitbucket.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A changeset, <i>aka</i> commit.
 * 
 * @author ericbottard
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketChangeset {

    /**
     * A modification on a single file.
     * 
     * @author ericbottard
     * 
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FileModification {

        @JsonProperty
        private String file;

        @JsonProperty
        private FileModificationType type;

        /**
         * The path of the file (or directory) affected.
         */
        public String getFile() {
            return file;
        }

        /**
         * The kind of modification.
         */
        public FileModificationType getType() {
            return type;
        }
    }

    /**
     * The kind of modification on a file path.
     */
    public static enum FileModificationType {
        modified, added, removed;
    }

    @JsonProperty
    private String author;

    @JsonProperty
    private String branch;

    @JsonProperty
    private List<FileModification> files;

    @JsonProperty
    private String message;

    @JsonProperty
    private String node;

    @JsonProperty
    private List<String> parents;

    @JsonProperty("raw_author")
    private String rawAuthor;

    @JsonProperty("raw_node")
    private String rawNode;

    @JsonProperty
    private long revision;

    /**
     * The username of the {@link BitBucketUser} that made the change.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * The name of the branch where the commit occurred.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * A list of affected files.
     */
    public List<FileModification> getFiles() {
        return files;
    }

    /**
     * The commit message as entered by the {@link #getAuthor() author}.
     */
    public String getMessage() {
        return message;
    }

    /**
     * The short node hash.
     */
    public String getNode() {
        return node;
    }

    /**
     * The parent commit(s) of this change.
     */
    public List<String> getParents() {
        return parents;
    }

    /**
     * The raw author identifier of this change (includes email address).
     */
    public String getRawAuthor() {
        return rawAuthor;
    }

    /**
     * The full node hash.
     */
    public String getRawNode() {
        return rawNode;
    }

    public long getRevision() {
        return revision;
    }

    @Override
    public String toString() {
        return getNode();
    }

}
