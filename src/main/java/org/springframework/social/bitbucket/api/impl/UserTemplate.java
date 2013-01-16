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

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.UserOperations;
import org.springframework.social.bitbucket.api.UserWithRepositories;
import org.springframework.web.client.RestTemplate;

class UserTemplate extends AbstractBitBucketOperations implements
        UserOperations {

    public UserTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized);
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
