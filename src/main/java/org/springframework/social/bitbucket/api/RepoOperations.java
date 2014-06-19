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
package org.springframework.social.bitbucket.api;

import java.util.List;
import java.util.Map;

/**
 * BitBucket sub-API that allows interaction with code repositories.
 *
 * @author ericbottard
 */
public interface RepoOperations {

    /**
     * Returns the public repository that belongs to {@code user} and that has
     * {@code repoSlug} as its technical name.
     */
    BitBucketRepository getRepository(String user, String repoSlug);

    /**
     * Returns the list of repositories for the current user.
     */
    List<BitBucketRepository> getUserRepositories();

    /**
     * Search for public repositories whose name contains the given String.
     */
    List<BitBucketRepository> search(String string);

    /**
     * Returns a mapping between tags (keys) and changesets (values) on the
     * given repository.
     */
    Map<String, BitBucketChangeset> getTags(String user, String repoSlug);

    /**
     * Returns the list of users following the given repository.
     */
    List<BitBucketUser> getFollowers(String user, String repoSlug);

    /**
     * Returns the last changesets on a repository, as well as information about
     * how many are available totally.
     */
    BitBucketChangesets getChangesets(String user, String repoSlug);

    /**
     * Returns some changesets on a repository, as well as information about how
     * many are available totally.
     */
    BitBucketChangesets getChangesets(String user, String repoSlug,
                                      String start, int limit);

    /**
     * Returns information about a known directory, including children
     * directories and files.
     */
    BitBucketDirectory getDirectory(String user, String repoSlug,
                                    String revision, String path);

    /**
     * Returns information and actual contents (as a String) about a known file
     * path.
     */
    BitBucketFile getFile(String string, String string2, String string3,
                          String string4);

    /**
     * Creates a new repository under the account of the currently authenticated
     * user. The account automatically becomes the owner.
     */
    BitBucketRepository createRepository(RepoCreation options);
}
