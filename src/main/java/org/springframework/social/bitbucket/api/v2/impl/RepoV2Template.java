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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.*;
import org.springframework.web.client.RestTemplate;

public class RepoV2Template extends AbstractBitBucketOperations implements RepoV2Operations {

    public RepoV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public RepositoryPage getRepositories() {
        return restTemplate.getForObject(
                buildUrl("/repositories"),
                RepositoryPage.class);
    }

    @Override
    public RepositoryPage getRepositories(String owner) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{owner}"),
                RepositoryPage.class, owner);
    }

    @Override
    public RepositoryPage getRepositories(BitBucketV2User user) {
        return getRepositories(user.getUsername());
    }

    @Override
    public RepositoryPage getNextPage(RepositoryPage page) {
        return restTemplate.getForObject(
                page.getNext(),
                RepositoryPage.class);
    }

}
