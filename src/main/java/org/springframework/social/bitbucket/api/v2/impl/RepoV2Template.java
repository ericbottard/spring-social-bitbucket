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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.RepoV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import static com.google.common.base.Preconditions.*;

public class RepoV2Template extends AbstractBitBucketOperations implements RepoV2Operations {

    public static final String OWNER_IS_REQUIRED = "owner is required";
    public static final String REPO_SLUG_IS_REQUIRED = "repoSlug is required";

    public RepoV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
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
    public final BitBucketV2Repository getRepository(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}"),
                BitBucketV2Repository.class, owner, repoSlug);
    }

    @Override
    public final BitBucketV2Repository getRepository(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return getRepository(owner.getUsername(), repoSlug);
    }

    @Override
    public final void createRepository(String owner, String repoSlug, BitBucketV2RepositoryCreation repository) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        checkNotNull(repository, "repository is required");
        restTemplate.postForObject(
                buildUrl("/repositories/{owner}/{repo_slug}"),
                repository,
                null,
                owner,
                repoSlug
        );
    }

    @Override
    public final void createRepository(BitBucketV2Account owner, String repoSlug, BitBucketV2RepositoryCreation repository) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        checkNotNull(repository, "repository is required");
        createRepository(owner.getUsername(), repoSlug, repository);
    }

    @Override
    public final void deleteRepository(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        restTemplate.delete(buildUrl("/repositories/{owner}/{repo_slug}"), owner, repoSlug);
    }

    @Override
    public final void deleteRepository(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        deleteRepository(owner.getUsername(), repoSlug);
    }

    @Override
    public final AccountPage getWatchers(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}/watchers"),
                AccountPage.class, owner, repoSlug);
    }

    @Override
    public final AccountPage getWatchers(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return getWatchers(owner.getUsername(), repoSlug);
    }

    @Override
    public final RepositoryPage getForks(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}/forks"),
                RepositoryPage.class, owner, repoSlug);
    }

    @Override
    public final RepositoryPage getForks(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, OWNER_IS_REQUIRED);
        checkArgument(StringUtils.hasText(repoSlug), REPO_SLUG_IS_REQUIRED);
        return getForks(owner.getUsername(), repoSlug);
    }

    @Override
    public final RepositoryPage getNextPage(RepositoryPage page) {
        checkNotNull(page, "page is required");
        checkState(page.hasNext(), "this page does not have a next page");
        return restTemplate.getForObject(
                page.getNext(),
                RepositoryPage.class);
    }

    @Override
    public final RepositoryPage getPreviousPage(RepositoryPage page) {
        checkNotNull(page, "page is required");
        checkState(page.hasNext(), "this page does not have a previous page");
        return restTemplate.getForObject(
                page.getPrevious(),
                RepositoryPage.class);
    }

}
