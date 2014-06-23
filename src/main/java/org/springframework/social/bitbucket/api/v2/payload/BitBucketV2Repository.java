/**
 * Copyright (C) 2012 Eric Bottard / Guillaume Lederrey (eric.bottard+ghpublic@gmail.com / guillaume.lederrey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.bitbucket.api.v2.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketSCM;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2Repository {

    @JsonProperty
    private BitBucketSCM scm;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    @JsonProperty
    private String description;

    //private List<Object> links;
    @JsonProperty
    private LinkList links;

    public LinkList getLinks() {
        return links;
    }

    @JsonProperty("fork_policy")
    private BitBucketForkPolicy forkPolicy;

    @JsonProperty
    private String language;

//    @JsonProperty("created_on")
//    @JsonDeserialize(using = UTCDateDeserializer.class)
//    private Date createdOn;

    // parent

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("has_issues")
    private boolean hasIssues;

    // owner
    @JsonProperty
    private BitBucketV2Account owner;

//    @JsonProperty("updated_on")
//    @JsonDeserialize(using = UTCDateDeserializer.class)
//    private Date updatedOn;

    @JsonProperty
    private BitBucketV2Repository parent;

    @JsonProperty
    private int size;

    @JsonProperty("is_private")
    private boolean isPrivate;

    @JsonProperty
    private String name;

    /**
     * The source control manager for the repository. This is either hg or git.
     */
    public final BitBucketSCM getScm() {
        return scm;
    }

    /**
     * A boolean indicating if the repository has a wiki.
     */
    public final boolean hasWiki() {
        return hasWiki;
    }

    /**
     * A string containing the repository's description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Control the rules for forking this repository.
     */
    public final BitBucketForkPolicy getForkPolicy() {
        return forkPolicy;
    }

    /**
     * The main (programming) language of the repository source files.
     */
    public final String getLanguage() {
        return language;
    }

    /**
     * The unique key into the repository. This key has the format: {owner}/{repo_slug}
     */
    public final String getFullName() {
        return fullName;
    }

    /**
     * A boolean indicating a repository has an issue tracker.
     */
    public final boolean hasIssues() {
        return hasIssues;
    }

    /**
     * The owner's account.
     */
    public final BitBucketV2Account getOwner() {
        return owner;
    }

    /**
     * The parent repository this repository was forked off (only present on forks). This is a repository object itself.
     */
    public final BitBucketV2Repository getParent() {
        return parent;
    }

    /**
     * The size of the repository in bytes.
     */
    public final int getSize() {
        return size;
    }

    /**
     * A boolean indicating if a repository is private or public.
     */
    public final boolean isPrivate() {
        return isPrivate;
    }

    /**
     * The display name of the repository.
     */
    public final String getName() {
        return name;
    }
}
