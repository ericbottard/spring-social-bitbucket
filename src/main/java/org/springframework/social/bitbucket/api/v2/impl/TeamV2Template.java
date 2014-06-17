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
import org.springframework.social.bitbucket.api.v2.BitBucketV2User;
import org.springframework.social.bitbucket.api.v2.RepositoryPage;
import org.springframework.social.bitbucket.api.v2.TeamV2Operations;
import org.springframework.social.bitbucket.api.v2.UserPage;
import org.springframework.web.client.RestTemplate;

public class TeamV2Template extends AbstractBitBucketOperations implements TeamV2Operations {

    public TeamV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public BitBucketV2User getTeam(String teamName) {
        return restTemplate.getForObject(
                buildUrl("/teams/{teamName}"),
                BitBucketV2User.class,
                teamName);
    }

    @Override
    public UserPage getMembers(String teamName) {
        return restTemplate.getForObject(
                buildUrl("/teams/{teamName}/members"),
                UserPage.class,
                teamName);
    }

    @Override
    public UserPage getMembers(BitBucketV2User team) {
        return getMembers(team.getUsername());
    }

    @Override
    public UserPage getFollowers(String teamName) {
        return restTemplate.getForObject(
                buildUrl("/teams/{teamName}/followers"),
                UserPage.class,
                teamName);
    }

    @Override
    public UserPage getFollowers(BitBucketV2User team) {
        return getFollowers(team.getUsername());
    }

    @Override
    public UserPage getFollowing(String teamName) {
        return restTemplate.getForObject(
                buildUrl("/teams/{teamName}/following"),
                UserPage.class,
                teamName);
    }
    @Override
    public UserPage getFollowing(BitBucketV2User team) {
        return getFollowing(team.getUsername());
    }

    @Override
    public RepositoryPage getRepositories(String teamName) {
        return restTemplate.getForObject(
                buildUrl("/teams/{teamName}/repositories"),
                RepositoryPage.class,
                teamName);
    }

    @Override
    public RepositoryPage getRepositories(BitBucketV2User team) {
        return getRepositories(team.getUsername());
    }

    @Override
    public RepositoryPage getNextPage(RepositoryPage page) {
        return restTemplate.getForObject(
                page.getNext(),
                RepositoryPage.class);
    }

    @Override
    public UserPage getNextPage(UserPage page) {
        return restTemplate.getForObject(
                page.getNext(),
                UserPage.class);
    }
}
