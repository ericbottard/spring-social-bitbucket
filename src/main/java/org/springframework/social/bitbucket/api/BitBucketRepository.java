/**
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.bitbucket.api;

import java.io.Serializable;
import java.util.Date;

import org.springframework.social.bitbucket.api.impl.UTCDateDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
