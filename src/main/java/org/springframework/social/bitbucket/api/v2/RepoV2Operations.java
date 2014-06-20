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
package org.springframework.social.bitbucket.api.v2;

import org.springframework.social.bitbucket.api.v2.payload.*;

public interface RepoV2Operations {

    /**
     * GET a list of repositories for an account.
     * <p/>
     * Gets the list of repositories associated with an account. If the caller is properly authenticated and authorized,
     * this method returns a collection containing public and private repositories. Otherwise, this method returns a
     * collection of the public repositories. This produces a paginated response.
     *
     * @param owner The account of the repo owner.
     */
    RepositoryPage getRepositories(String owner);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#getRepositories(String)
     */
    RepositoryPage getRepositories(BitBucketV2Account owner);

    /**
     * GET a list of all public repositories.
     * <p/>
     * Gets a list of all the public repositories on Bitbucket.  This produces a paginated response. Pagination only
     * goes forward (it's not possible to navigate to previous pages) and navigation is done by following the URL for
     * the next page.
     * <p/>
     * The returned repositories are ordered by creation date, oldest repositories first. Only public repositories are
     * returned.
     */
    RepositoryPage getRepositories();

    /**
     * GET a repository.
     * <p/>
     * Returns a single repository.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    BitBucketV2Repository getRepository(String owner, String repoSlug);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#getRepository(String, String)
     */
    BitBucketV2Repository getRepository(BitBucketV2Account owner, String repoSlug);

    /**
     * Create a new repository.
     * <p/>
     * At the moment, this call does not return anything as the response is not documented by BitBucket.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    void createRepository(String owner, String repoSlug, BitBucketV2RepositoryCreation repository);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#createRepository(String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2RepositoryCreation)
     */
    void createRepository(BitBucketV2Account owner, String repoSlug, BitBucketV2RepositoryCreation repository);

    /**
     * Removes a repository.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    void deleteRepository(String owner, String repoSlug);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#deleteRepository(String, String)
     */
    void deleteRepository(BitBucketV2Account owner, String repoSlug);

    /**
     * Gets the list of accounts watching a repository.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    AccountPage getWatchers(String owner, String repoSlug);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#getWatchers(String, String)
     */
    AccountPage getWatchers(BitBucketV2Account owner, String repoSlug);

    /**
     * Gets the list of repository forks, This call returns a repository object for each fork.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    RepositoryPage getForks(String owner, String repoSlug);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepoV2Operations#getForks(String, String)
     */
    RepositoryPage getForks(BitBucketV2Account owner, String repoSlug);

    RepositoryPage getNextPage(RepositoryPage page);

    RepositoryPage getPreviousPage(RepositoryPage page);

}
