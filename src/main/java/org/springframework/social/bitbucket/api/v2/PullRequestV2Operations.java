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
package org.springframework.social.bitbucket.api.v2;

import org.springframework.social.bitbucket.api.v2.payload.*;

import java.util.Set;

public interface PullRequestV2Operations {

    /**
     * Get a list of of a repository's open pull requests.
     * <p/>
     * This returns a paginated list of pull request objects. Note that the participants list is not included in the
     * pull requests. To obtain the full list of participants, follow the pull requests's self link.
     * <p/>
     * By default, only the open pull requests are returned. Use the state query parameter to access declined
     * (state=DECLINED), open (state=OPEN) or merged (state=MERGED). The state parameter can be repeated to include pull
     * requests in different states.
     *
     * @param owner    The account owning the repo.
     * @param repoSlug The repo identifier.
     * @param states   Filter the pull requests on those states. If null or empty, only open pull requests are returned.
     */
    PullRequestPage getPullRequests(String owner, String repoSlug, Set<BitBucketV2PullRequestState> states);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#getPullRequests(String, String, java.util.Set)
     */
    PullRequestPage getPullRequests(BitBucketV2Account owner, String repoSlug, Set<BitBucketV2PullRequestState> states);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#getPullRequests(String, String, java.util.Set)
     */
    PullRequestPage getPullRequests(BitBucketV2Repository repository, Set<BitBucketV2PullRequestState> states);

    /**
     * Creates a new pull request. The request URL you provide is the destination repository URL. For this reason, you
     * must specify an explicit source repository in the request object if you want to pull from a different repository.
     *
     * @param owner    Owner of the destination repository.
     * @param repoSlug The repo identifier of the destination repository.
     */
    BitBucketV2PullRequest createPullRequest(
            String owner,
            String repoSlug,
            BitBucketV2PullRequest pullRequest);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#createPullRequest(String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2PullRequest)
     */
    BitBucketV2PullRequest createPullRequest(
            BitBucketV2Account owner,
            String repoSlug,
            BitBucketV2PullRequest pullRequest);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#createPullRequest(String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2PullRequest)
     */
    BitBucketV2PullRequest createPullRequest(
            BitBucketV2Repository repository,
            BitBucketV2PullRequest pullRequest);

    /**
     * Updates an existing pull request.
     * <p/>
     * The pull request's status must be open. With the exception of the source and destination parameters, the request
     * body must include all the existing request parameters; Omitting a parameter causes the server to drop the
     * existing value. For example, if the pull requests already has 3 reviewers, the request body must include these 3
     * reviewers to prevent Bitbucket from dropping them.
     * <p/>
     * A pull request cannot exist without the source and destination parameters. For this reason Bitbucket never drops
     * them. The URL provides the destination and Bitbucket keeps the existing source.
     * <p/>
     * A caller cannot change any elements of the source repository or the destination repository's name. A caller can
     * change the destination branch. To do this, provide the destination's branch element in the call.
     * <p/>
     * A pull request is stale when new commits were pushed to the source branch but the request does not yet reflect
     * these new commits. When a PUT is called on a stale request, Bitbucket always updates the source branch to the new
     * head commit before processing the PUT.
     */
    BitBucketV2PullRequest updatePullRequest(
            String owner, String repoSlug, String id, BitBucketV2PullRequest pullRequest);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#updatePullRequest(String, String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2PullRequest)
     */
    BitBucketV2PullRequest updatePullRequest(
            BitBucketV2Account owner, String repoSlug, String id, BitBucketV2PullRequest pullRequest);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#updatePullRequest(String, String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2PullRequest)
     */
    BitBucketV2PullRequest updatePullRequest(
            BitBucketV2Repository repository, String id, BitBucketV2PullRequest pullRequest);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#updatePullRequest(String, String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2PullRequest)
     */
    BitBucketV2PullRequest updatePullRequest(
            BitBucketV2PullRequest existingPullRequest, BitBucketV2PullRequest updatedPullRequest);

    /**
     * Gets an specific pull request.
     *
     * @param owner    The account owning the repo.
     * @param repoSlug The repo identifier.
     * @param id       The pull request identifier.
     */
    BitBucketV2PullRequest getPullRequest(String owner, String repoSlug, String id);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#getPullRequest(String, String, String)
     */
    BitBucketV2PullRequest getPullRequest(BitBucketV2Account owner, String repoSlug, String id);

    /**
     * @see org.springframework.social.bitbucket.api.v2.PullRequestV2Operations#getPullRequest(String, String, String)
     */
    BitBucketV2PullRequest getPullRequest(BitBucketV2Repository repository, String id);

    // getCommits

    // approve

    // delete

    // getDiff

    // activityLogRepo
    // activityLogPR

    // merge PR

    // reject PR

    // getPRComments
    // getPRComment

}
