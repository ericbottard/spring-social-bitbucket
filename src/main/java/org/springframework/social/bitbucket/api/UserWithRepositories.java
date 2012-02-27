package org.springframework.social.bitbucket.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container class that holds basic information about an account as well as a
 * list of repositories.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithRepositories {

    @JsonProperty
    private BitBucketUser user;

    @JsonProperty
    private List<BitBucketRepository> repositories;

    public List<BitBucketRepository> getRepositories() {
        return repositories;
    }

    public BitBucketUser getUser() {
        return user;
    }

}