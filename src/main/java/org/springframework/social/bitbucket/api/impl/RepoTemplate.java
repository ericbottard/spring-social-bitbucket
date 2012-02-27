package org.springframework.social.bitbucket.api.impl;

import static java.util.Arrays.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketChangesets;
import org.springframework.social.bitbucket.api.BitBucketRepository;
import org.springframework.social.bitbucket.api.BitBucketChangeset;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.RepoOperations;
import org.springframework.web.client.RestTemplate;

public class RepoTemplate extends AbstractBitBucketOperations implements
        RepoOperations {

    private final RestTemplate restTemplate;

    public RepoTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
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
