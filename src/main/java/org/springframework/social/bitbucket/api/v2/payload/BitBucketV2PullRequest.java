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

import javax.annotation.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2PullRequest {

    @JsonProperty
    private BitBucketV2PullRequestState state;

    @JsonProperty
    private String description;

    // links
    // An map of links pointing to other representation and related data and functionality.

    @JsonProperty
    private String title;

    @JsonProperty("close_source_branch")
    private boolean closeSourceBranch;

    // destination
    // A structure containing the commit, branch and repository structures associated with the request destination repository.

    @JsonProperty
    @Nullable
    private String reason;

    @JsonProperty
    private String id;

    // source
    // A structure containing the commit , branch , and repository structures associated with the request source repository.

    // created_on
    // Date the request was created.

    @JsonProperty
    private BitBucketV2Account author;

    // updated_on
    // Date the request was last updated.

    // @Nullable
    // merge_commit
    // The merge commit object that was created when the pull request was accepted. This is only applicable to pull requests that are in fulfilled state.

    @JsonProperty("closed_by")
    @Nullable
    private BitBucketV2Account closedBy;

    // reviewers
    // The list of users that were added as reviewers on this pull request when it was created. For performance reasons, the API only returns this list when an API requests a pull request by id.

    // participants
    // The list of users that are collaborating on this pull request. Collaborators are user that:
    //   * are added to the pull request as a reviewer (part of the reviewers list)
    //   * are not explicit reviewers, but have commented on the pull request
    //   * are not explicit reviewers, but have approved the pull request
    // Each user is wrapped in an object that indicates the user's role and whether they have approved the pull request. For performance reasons, the API only returns this list when an API requests a pull request by id.

    /**
     * The pull request's current status. The status is either open, rejected, or fulfilled.
     */
    public final BitBucketV2PullRequestState getState() {
        return state;
    }

    /**
     * Description field for the request.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Title of the pull request.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * A boolean flag indicating if merging the pull request closes the source branch.
     */
    public final boolean isCloseSourceBranch() {
        return closeSourceBranch;
    }

    /**
     * Explains why a pull request was declined. This field is only applicable to pull requests in rejected state.
     */
    @Nullable
    public final String getReason() {
        return reason;
    }

    /**
     * The pull request's unique ID. Note that pull request IDs are only unique within their associated repository.
     */
    public final String getId() {
        return id;
    }

    /**
     * The user who created the pull request.
     */
    public final BitBucketV2Account getAuthor() {
        return author;
    }

    /**
     * The user that closed the request.
     */
    @Nullable
    public final BitBucketV2Account getClosedBy() {
        return closedBy;
    }
}
