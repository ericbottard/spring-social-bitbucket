/**
 * Copyright (C) 2012 Eric Bottard (eric.bottard+ghpublic@gmail.com)
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

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public class BitBucketV2RepositoryCreation {

    @JsonProperty
    private BitBucketV2Kind scm;

    @JsonProperty
    @Nullable
    private String name;

    @JsonProperty("is_private")
    private boolean isPrivate;

    @JsonProperty
    @Nullable
    private String description;

    @JsonProperty("forking_policy")
    private BitBucketForkPolicy forkingPolicy;

    @JsonProperty
    @Nullable
    private String language;

    @JsonProperty("has_issues")
    private boolean hasIssues;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    /**
     * The SCM type for the new repo. Either "hg" or "git". When omitted, the type of your most recently created
     * repository will be used (optional).
     */
    public final BitBucketV2Kind getScm() {
        return scm;
    }

    /**
     * The name for the repository. When omitted, this assumes that value of the repo_slug part of the URL (optional).
     */
    public final String getName() {
        return name;
    }

    /**
     * Whether the repository should be public (false) or private (true).
     */
    public final boolean isPrivate() {
        return isPrivate;
    }

    /**
     * The human readable description of the repository (optional).
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Control the rules for forking this repository.
     */
    public final BitBucketForkPolicy getForkingPolicy() {
        return forkingPolicy;
    }

    /**
     * The programming language used in the repository. Must be a valid (lowercase) item as shown in the drop-down list
     * on the repository's admin page (optional).
     */
    public final String getLanguage() {
        return language;
    }

    /**
     * Whether the repository should have an issue tracker (optional – defaults to false).
     */
    public final boolean hasIssues() {
        return hasIssues;
    }

    /**
     * Whether the repository should have a wiki (optional – defaults to false).
     */
    public final boolean hasWiki() {
        return hasWiki;
    }
}
