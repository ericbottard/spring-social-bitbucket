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

import org.springframework.social.bitbucket.api.v2.*;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class BitBucketV2Template extends AbstractOAuth1ApiBinding implements
        BitBucketV2 {

    public static final String OWNER_IS_REQUIRED = "owner is required";
    public static final String PAGE_IS_REQUIRED = "page is required";
    public static final String PAGE_DOES_NOT_HAVE_A_PREVIOUS_PAGE = "this page does not have a previous page";
    public static final String PAGE_DOES_NOT_HAVE_A_NEXT_PAGE = "this page does not have a next page";
    public static final String REPO_SLUG_IS_REQUIRED = "repoSlug is required";
    private RepositoriesV2Operations repositoriesV2Operations;

    private TeamV2Operations teamV2Operations;

    private UserV2Operations userV2Operations;

    private PageV2Operations pageV2Operations;

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
        repositoriesV2Operations = new RepositoriesV2Template(getRestTemplate(), isAuthorized());
        teamV2Operations = new TeamV2Template(getRestTemplate(), isAuthorized());
        userV2Operations = new UserV2Template(getRestTemplate(), isAuthorized());
        pageV2Operations = new PageV2Template(getRestTemplate(), isAuthorized());
    }

    @Override
    public final RepositoriesV2Operations getRepositoriesOperations() {
        return repositoriesV2Operations;
    }

    @Override
    public final TeamV2Operations getTeamOperations() {
        return teamV2Operations;
    }

    @Override
    public final UserV2Operations getUserOperations() {
        return userV2Operations;
    }

    @Override
    public final PageV2Operations getPageOperations() {
        return pageV2Operations;
    }
}
