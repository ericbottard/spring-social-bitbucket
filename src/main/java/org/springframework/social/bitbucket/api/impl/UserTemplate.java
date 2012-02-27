package org.springframework.social.bitbucket.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.UserOperations;
import org.springframework.social.bitbucket.api.UserWithRepositories;
import org.springframework.web.client.RestTemplate;

class UserTemplate extends AbstractBitBucketOperations implements
        UserOperations {

    private final RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public UserWithRepositories getUserWithRepositories() {
        return restTemplate.getForObject(buildUrl("/user"),
                UserWithRepositories.class);
    }

    @Override
    public List<BitBucketUser> getFollowers(String user) {
        return restTemplate.getForObject(buildUrl("/users/{user}/followers"),
                FollowersHolder.class, user).followers;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class FollowersHolder {
        @JsonProperty
        private List<BitBucketUser> followers;
    }

}
