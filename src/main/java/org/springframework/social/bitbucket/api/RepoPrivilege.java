package org.springframework.social.bitbucket.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoPrivilege {

    @JsonProperty("repo")
    private String repository;

    @JsonProperty
    private BitBucketPrivilege privilege;

    @JsonProperty
    private BitBucketUser user;

    public String getRepository() {
        return repository;
    }

    public BitBucketPrivilege getPrivilege() {
        return privilege;
    }

    public BitBucketUser getUser() {
        return user;
    }

}
