/**
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.RepoV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import static com.google.common.base.Preconditions.*;

public class RepoV2Template extends AbstractBitBucketOperations implements RepoV2Operations {

    public RepoV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public RepositoryPage getRepositories() {
        return restTemplate.getForObject(
                buildUrl("/repositories"),
                RepositoryPage.class);
    }

    @Override
    public RepositoryPage getRepositories(String owner) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}"),
                RepositoryPage.class, owner);
    }

    @Override
    public RepositoryPage getRepositories(BitBucketV2Account owner) {
        checkNotNull(owner, "owner is required");
        return getRepositories(owner.getUsername());
    }

    @Override
    public BitBucketV2Repository getRepository(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}"),
                BitBucketV2Repository.class, owner, repoSlug);
    }

    @Override
    public BitBucketV2Repository getRepository(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return getRepository(owner.getUsername(), repoSlug);
    }

    @Override
    public void createRepository(String owner, String repoSlug, BitBucketV2RepositoryCreation repository) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
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
    public void createRepository(BitBucketV2Account owner, String repoSlug, BitBucketV2RepositoryCreation repository) {
        checkNotNull(owner, "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        checkNotNull(repository, "repository is required");
        createRepository(owner.getUsername(), repoSlug, repository);
    }

    @Override
    public void deleteRepository(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        restTemplate.delete(buildUrl("/repositories/{owner}/{repo_slug}"), owner, repoSlug);
    }

    @Override
    public void deleteRepository(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        deleteRepository(owner.getUsername(), repoSlug);
    }

    @Override
    public AccountPage getWatchers(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}/watchers"),
                AccountPage.class, owner, repoSlug);
    }

    @Override
    public AccountPage getWatchers(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return getWatchers(owner.getUsername(), repoSlug);
    }

    @Override
    public RepositoryPage getForks(String owner, String repoSlug) {
        checkArgument(StringUtils.hasText(owner), "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}/{repo_slug}/forks"),
                RepositoryPage.class, owner, repoSlug);
    }

    @Override
    public RepositoryPage getForks(BitBucketV2Account owner, String repoSlug) {
        checkNotNull(owner, "owner is required");
        checkArgument(StringUtils.hasText(repoSlug), "repoSlug is required");
        return getForks(owner.getUsername(), repoSlug);
    }

    @Override
    public RepositoryPage getNextPage(RepositoryPage page) {
        checkNotNull(page, "page is required");
        checkState(page.hasNext(), "this page does not have a next page");
        return restTemplate.getForObject(
                page.getNext(),
                RepositoryPage.class);
    }

    @Override
    public RepositoryPage getPreviousPage(RepositoryPage page) {
        checkNotNull(page, "page is required");
        checkState(page.hasNext(), "this page does not have a previous page");
        return restTemplate.getForObject(
                page.getPrevious(),
                RepositoryPage.class);
    }

}
