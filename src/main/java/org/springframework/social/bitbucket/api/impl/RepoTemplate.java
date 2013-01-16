/**
 * Copyright 2012 the original author or authors.
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
package org.springframework.social.bitbucket.api.impl;

import static java.util.Arrays.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketChangeset;
import org.springframework.social.bitbucket.api.BitBucketChangesets;
import org.springframework.social.bitbucket.api.BitBucketDirectory;
import org.springframework.social.bitbucket.api.BitBucketFile;
import org.springframework.social.bitbucket.api.BitBucketRepository;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.RepoCreation;
import org.springframework.social.bitbucket.api.RepoOperations;
import org.springframework.web.client.RestTemplate;

public class RepoTemplate extends AbstractBitBucketOperations implements
        RepoOperations {

    public RepoTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized);
    }

    @Override
    public BitBucketRepository getRepository(String user, String repoSlug) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/").toString(),
                BitBucketRepository.class, user, repoSlug);
    }

    @Override
    public List<BitBucketRepository> getUserRepositories() {
        return asList(restTemplate.getForObject(
                buildUrl("/user/repositories/"), BitBucketRepository[].class));
    }

    @Override
    public List<BitBucketRepository> search(String query) {
        return restTemplate.getForObject(buildUrl("/repositories/?name={q}"),
                SearchResultHolder.class, query).repositories;
    }

    @Override
    public Map<String, BitBucketChangeset> getTags(String user, String repoSlug) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/tags/").toString(),
                Tags.class, user, repoSlug);

    }

    @Override
    public List<BitBucketUser> getFollowers(String user, String repoSlug) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/followers/").toString(),
                FollowersHolder.class, user, repoSlug).followers;
    }

    @Override
    public BitBucketChangesets getChangesets(String user, String repoSlug) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/changesets/").toString(),
                BitBucketChangesets.class, user, repoSlug);
    }

    @Override
    public BitBucketChangesets getChangesets(String user, String repoSlug,
            String start, int limit) {
        return restTemplate
                .getForObject(
                        buildUrl(
                                "/repositories/{user}/{slug}/changesets/?start={start}&limit={limit}")
                                .toString(), BitBucketChangesets.class, user,
                        repoSlug, start, limit);
    }

    @Override
    public BitBucketDirectory getDirectory(String user, String repoSlug,
            String revision, String path) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/src/{rev}/{path}/")
                        .toString(), BitBucketDirectory.class, user, repoSlug,
                revision, path);
    }

    @Override
    public BitBucketFile getFile(String user, String repoSlug, String revision,
            String path) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{user}/{slug}/src/{rev}/{path}")
                        .toString(), BitBucketFile.class, user, repoSlug,
                revision, path);
    }

    @Override
    public BitBucketRepository createRepository(RepoCreation options) {
        return restTemplate.postForObject(buildUrl("/repositories"), options,
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
