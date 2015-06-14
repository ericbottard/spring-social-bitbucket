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

import org.springframework.social.bitbucket.api.v2.payload.AccountPage;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Repository;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;

public interface RepositoryV2Operations {

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
     * @see org.springframework.social.bitbucket.api.v2.RepositoryV2Operations#getRepository(String, String)
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
    void createRepository(String owner, String repoSlug, BitBucketV2Repository repository);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepositoryV2Operations#createRepository(String, String, org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Repository)
     */
    void createRepository(BitBucketV2Account owner, String repoSlug, BitBucketV2Repository repository);

    /**
     * Removes a repository.
     *
     * @param owner    The accountname of the repo owner.
     * @param repoSlug The repository slug.
     */
    void deleteRepository(String owner, String repoSlug);

    /**
     * @see org.springframework.social.bitbucket.api.v2.RepositoryV2Operations#deleteRepository(String, String)
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
     * @see org.springframework.social.bitbucket.api.v2.RepositoryV2Operations#getWatchers(String, String)
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
     * @see org.springframework.social.bitbucket.api.v2.RepositoryV2Operations#getForks(String, String)
     */
    RepositoryPage getForks(BitBucketV2Account owner, String repoSlug);

}
