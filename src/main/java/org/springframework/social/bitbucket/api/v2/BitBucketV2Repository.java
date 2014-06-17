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
package org.springframework.social.bitbucket.api.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketSCM;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2Repository {

    @JsonProperty
    private BitBucketSCM scm;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    @JsonProperty
    private String description;

    private List<Object> links;

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
    private BitBucketV2User owner;

//    @JsonProperty("updated_on")
//    @JsonDeserialize(using = UTCDateDeserializer.class)
//    private Date updatedOn;

    @JsonProperty
    private int size;

    @JsonProperty("is_private")
    private boolean isPrivate;

    @JsonProperty
    private String name;

    public BitBucketSCM getScm() {
        return scm;
    }

    public boolean hasWiki() {
        return hasWiki;
    }

    public String getDescription() {
        return description;
    }

    public BitBucketForkPolicy getForkPolicy() {
        return forkPolicy;
    }

    public String getLanguage() {
        return language;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean hasIssues() {
        return hasIssues;
    }

    public BitBucketV2User getOwner() {
        return owner;
    }

    public int getSize() {
        return size;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getName() {
        return name;
    }
}
