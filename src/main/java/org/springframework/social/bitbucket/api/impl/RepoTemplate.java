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
package org.springframework.social.bitbucket.api.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.bitbucket.api.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class RepoTemplate extends AbstractBitBucketOperations implements
        RepoOperations {

    public RepoTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V1);
    }

    @Override
    public final BitBucketRepository getRepository(String user, String repoSlug) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/"),
                BitBucketRepository.class, user, repoSlug);
    }

    @Override
    public final List<BitBucketRepository> getUserRepositories() {
        return asList(getRestTemplate().getForObject(
                buildUrl("/user/repositories/"), BitBucketRepository[].class));
    }

    @Override
    public final List<BitBucketRepository> search(String query) {
        return getRestTemplate().getForObject(buildUrl("/repositories/?name={q}"),
                SearchResultHolder.class, query).repositories;
    }

    @Override
    public final Map<String, BitBucketChangeset> getTags(String user, String repoSlug) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/tags/"),
                Tags.class, user, repoSlug);

    }

    @Override
    public final List<BitBucketUser> getFollowers(String user, String repoSlug) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/followers/"),
                FollowersHolder.class, user, repoSlug).followers;
    }

    @Override
    public final BitBucketChangesets getChangesets(String user, String repoSlug) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/changesets/"),
                BitBucketChangesets.class, user, repoSlug);
    }

    @Override
    public final BitBucketChangesets getChangesets(String user, String repoSlug,
                                                   String start, int limit) {
        return getRestTemplate()
                .getForObject(
                        buildUrl(
                                "/repositories/{user}/{slug}/changesets/?start={start}&limit={limit}"),
                        BitBucketChangesets.class, user,
                        repoSlug, start, limit);
    }

    @Override
    public final BitBucketDirectory getDirectory(String user, String repoSlug,
                                                 String revision, String path) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/src/{rev}/{path}/"),
                BitBucketDirectory.class, user, repoSlug,
                revision, path);
    }

    @Override
    public final BitBucketFile getFile(String user, String repoSlug, String revision,
                                       String path) {
        return getRestTemplate().getForObject(
                buildUrl("/repositories/{user}/{slug}/src/{rev}/{path}"),
                BitBucketFile.class, user, repoSlug,
                revision, path);
    }

    @Override
    public final BitBucketRepository createRepository(RepoCreation options) {
        return getRestTemplate().postForObject(buildUrl("/repositories"), options,
                BitBucketRepository.class);
    }

    /**
     * Exists for the sole purpose of having a strongly typed Map for Jackson.
     */
    private static class Tags extends HashMap<String, BitBucketChangeset> {
        private static final long serialVersionUID = 1L;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class FollowersHolder {

        @JsonProperty
        private List<BitBucketUser> followers;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SearchResultHolder {

        @JsonProperty
        private List<BitBucketRepository> repositories;
    }

}
