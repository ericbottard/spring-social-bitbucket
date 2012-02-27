package org.springframework.social.bitbucket.api;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.bitbucket.api.impl.UTCDateDeserializer;

/**
 * A BitBucket repository, possibly with additional metadata.
 * 
 * @author ericbottard
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("is_private")
    private boolean _private;

    @JsonProperty("utc_created_on")
    @JsonDeserialize(using = UTCDateDeserializer.class)
    private Date createdAt;

    @JsonProperty
    private String description;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    @JsonProperty("utc_last_updated")
    @JsonDeserialize(using = UTCDateDeserializer.class)
    private Date lastUpdatedOn;

    @JsonProperty
    private String name;

    @JsonProperty
    private String owner;

    @JsonProperty("read_only")
    private boolean readOnly;

    @JsonProperty
    private BitBucketSCM scm;

    @JsonProperty
    private long size;

    @JsonProperty
    private String slug;

    /**
     * The date when the repository was created.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * A description of the repository, as entered by its creator.
     */
    public String getDescription() {
        return description;
    }

    /**
     * When this repository was last updated.
     * 
     */
    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    /**
     * A user friendly name for this repository (may differ from its
     * {@link #getSlug() slug}).
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * The username of the repository owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * The source control management system this repository uses.
     */
    public BitBucketSCM getScm() {
        return scm;
    }

    /**
     * The size of this repository, in bytes.
     */
    public long getSize() {
        return size;
    }

    /**
     * This repository's "slug", <i>ie.</i> its technical id in BitBucket terms.
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Whether this repository has an attached wiki.
     */
    public boolean isHasWiki() {
        return hasWiki;
    }

    /**
     * Whether this repository is private (authenticated user needs access to
     * it) or is for everyone to see.
     */
    public boolean isPrivate() {
        return _private;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

}
