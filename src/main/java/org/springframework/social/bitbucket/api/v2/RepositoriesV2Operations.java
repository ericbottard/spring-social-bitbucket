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

import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;

public interface RepositoriesV2Operations {

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
     * @see RepositoriesV2Operations#getRepositories(String)
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

    BranchRestrictionsV2Operations getBranchRestrictionsOperations();

    CommitsV2Operations getCommitsOperations();

    DiffV2Operations getDiffOperations();

    PullRequestV2Operations getPullRequestOperations();

    RepositoryV2Operations getRepositoryOperations();

}
