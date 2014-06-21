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
import org.springframework.social.bitbucket.api.v2.*;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.social.bitbucket.api.v2.impl.BitBucketV2Template.OWNER_IS_REQUIRED;

public class RepositoriesV2Template extends AbstractBitBucketOperations implements RepositoriesV2Operations {

    private final BranchRestrictionsV2Operations branchRestrictionsV2Operations;
    private final CommitsV2Operations commitsV2Operations;
    private final DiffV2Operations diffV2Operations;
    private final PullRequestV2Operations pullRequestV2Operations;
    private final RepositoryV2Operations repositoryV2Operations;

    public RepositoriesV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
        branchRestrictionsV2Operations = new BranchRestrictionsV2Template(restTemplate, authorized);
        commitsV2Operations = new CommitsV2Template(restTemplate, authorized);
        diffV2Operations = new DiffV2Template(restTemplate, authorized);
        repositoryV2Operations = new RepositoryV2Template(restTemplate, authorized);
        pullRequestV2Operations = new PullRequestV2Template(restTemplate, authorized);
    }

    @Override
    public final RepositoryPage getRepositories() {
        return restTemplate.getForObject(
                buildUrl("/repositories"),
                RepositoryPage.class);
    }

    @Override
    public final RepositoryPage getRepositories(String owner) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}"),
                RepositoryPage.class, owner);
    }

    @Override
    public final RepositoryPage getRepositories(BitBucketV2Account owner) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        return getRepositories(owner.getUsername());
    }

    @Override
    public final BranchRestrictionsV2Operations getBranchRestrictionsOperations() {
        return branchRestrictionsV2Operations;
    }

    @Override
    public final CommitsV2Operations getCommitsOperations() {
        return commitsV2Operations;
    }

    @Override
    public final DiffV2Operations getDiffOperations() {
        return diffV2Operations;
    }

    @Override
    public final PullRequestV2Operations getPullRequestOperations() {
        return pullRequestV2Operations;
    }

    @Override
    public final RepositoryV2Operations getRepositoryOperations() {
        return repositoryV2Operations;
    }
}
