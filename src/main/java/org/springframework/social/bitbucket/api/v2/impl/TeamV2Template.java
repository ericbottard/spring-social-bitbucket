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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.TeamV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.AccountPage;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;
import org.springframework.web.client.RestTemplate;

public class TeamV2Template extends AbstractBitBucketOperations implements TeamV2Operations {

    public TeamV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public final BitBucketV2Account getTeam(String teamName) {
        return getRestTemplate().getForObject(
                buildUrl("/teams/{teamName}"),
                BitBucketV2Account.class,
                teamName);
    }

    @Override
    public final AccountPage getMembers(String teamName) {
        return getRestTemplate().getForObject(
                buildUrl("/teams/{teamName}/members"),
                AccountPage.class,
                teamName);
    }

    @Override
    public final AccountPage getMembers(BitBucketV2Account team) {
        return getMembers(team.getUsername());
    }

    @Override
    public final AccountPage getFollowers(String teamName) {
        return getRestTemplate().getForObject(
                buildUrl("/teams/{teamName}/followers"),
                AccountPage.class,
                teamName);
    }

    @Override
    public final AccountPage getFollowers(BitBucketV2Account team) {
        return getFollowers(team.getUsername());
    }

    @Override
    public final AccountPage getFollowing(String teamName) {
        return getRestTemplate().getForObject(
                buildUrl("/teams/{teamName}/following"),
                AccountPage.class,
                teamName);
    }

    @Override
    public final AccountPage getFollowing(BitBucketV2Account team) {
        return getFollowing(team.getUsername());
    }

    @Override
    public final RepositoryPage getRepositories(String teamName) {
        return getRestTemplate().getForObject(
                buildUrl("/teams/{teamName}/repositories"),
                RepositoryPage.class,
                teamName);
    }

    @Override
    public final RepositoryPage getRepositories(BitBucketV2Account team) {
        return getRepositories(team.getUsername());
    }

}
