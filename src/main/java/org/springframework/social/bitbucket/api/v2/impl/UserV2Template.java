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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.UserV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.AccountPage;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;
import org.springframework.web.client.RestTemplate;

public class UserV2Template extends AbstractBitBucketOperations implements UserV2Operations {

    public UserV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public BitBucketV2Account getUser(String username) {
        return restTemplate.getForObject(
                buildUrl("/users/{username}"),
                BitBucketV2Account.class,
                username);
    }

    @Override
    public AccountPage getFollowers(String username) {
        return restTemplate.getForObject(
                buildUrl("/users/{username}/followers"),
                AccountPage.class,
                username);
    }

    @Override
    public AccountPage getFollowers(BitBucketV2Account user) {
        return getFollowers(user.getUsername());
    }

    @Override
    public AccountPage getFollowing(String username) {
        return restTemplate.getForObject(
                buildUrl("/users/{username}/following"),
                AccountPage.class,
                username);
    }

    @Override
    public AccountPage getFollowing(BitBucketV2Account user) {
        return getFollowing(user.getUsername());
    }

    @Override
    public RepositoryPage getRepositories(String username) {
        return restTemplate.getForObject(
                buildUrl("/repositories/{username}"),
                RepositoryPage.class,
                username);
    }

    @Override
    public RepositoryPage getRepositories(BitBucketV2Account user) {
        return getRepositories(user.getUsername());
    }

    @Override
    public AccountPage getNextPage(AccountPage page) {
        return restTemplate.getForObject(
                page.getNext(),
                AccountPage.class);
    }

    @Override
    public RepositoryPage getNextPage(RepositoryPage page) {
        return restTemplate.getForObject(
                page.getNext(),
                RepositoryPage.class);
    }
}
