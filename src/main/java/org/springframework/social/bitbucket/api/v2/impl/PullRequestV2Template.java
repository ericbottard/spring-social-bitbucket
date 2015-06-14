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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.PullRequestV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class PullRequestV2Template extends AbstractBitBucketOperations implements PullRequestV2Operations {

    public static final String NOT_YET_IMPLEMENTED = "not yet implemented";

    public PullRequestV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public final PullRequestPage getPullRequests(String owner, String repoSlug, Set<BitBucketV2PullRequestState> states) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final PullRequestPage getPullRequests(BitBucketV2Account owner, String repoSlug, Set<BitBucketV2PullRequestState> states) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final PullRequestPage getPullRequests(BitBucketV2Repository repository, Set<BitBucketV2PullRequestState> states) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest createPullRequest(String owner, String repoSlug, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest createPullRequest(BitBucketV2Account owner, String repoSlug, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest createPullRequest(BitBucketV2Repository repository, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest updatePullRequest(String owner, String repoSlug, String id, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest updatePullRequest(BitBucketV2Account owner, String repoSlug, String id, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest updatePullRequest(BitBucketV2Repository repository, String id, BitBucketV2PullRequest pullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest updatePullRequest(BitBucketV2PullRequest existingPullRequest, BitBucketV2PullRequest updatedPullRequest) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest getPullRequest(String owner, String repoSlug, String id) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest getPullRequest(BitBucketV2Account owner, String repoSlug, String id) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public final BitBucketV2PullRequest getPullRequest(BitBucketV2Repository repository, String id) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }
}
