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

import org.springframework.social.bitbucket.api.v2.BitBucketV2;
import org.springframework.social.bitbucket.api.v2.RepoV2Operations;
import org.springframework.social.bitbucket.api.v2.TeamV2Operations;
import org.springframework.social.bitbucket.api.v2.UserV2Operations;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class BitBucketV2Template extends AbstractOAuth1ApiBinding implements
        BitBucketV2 {

    private RepoV2Operations repoV2Operations;

    private TeamV2Operations teamV2Operations;

    private UserV2Operations userV2Operations;

    public BitBucketV2Template(String consumerKey, String consumerSecret,
                               String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        initSubApis();
    }

    public BitBucketV2Template() {
        super();
        initSubApis();
    }

    private void initSubApis() {
        repoV2Operations = new RepoV2Template(getRestTemplate(), isAuthorized());
        teamV2Operations = new TeamV2Template(getRestTemplate(), isAuthorized());
        userV2Operations = new UserV2Template(getRestTemplate(), isAuthorized());
    }

    @Override
    public RepoV2Operations getRepoOperations() {
        return repoV2Operations;
    }

    @Override
    public TeamV2Operations getTeamOperations() {
        return teamV2Operations;
    }

    @Override
    public UserV2Operations getUserOperations() {
        return userV2Operations;
    }
}
