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
package org.springframework.social.bitbucket.api.impl;

import org.springframework.social.bitbucket.api.*;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class BitBucketTemplate extends AbstractOAuth1ApiBinding implements
        BitBucket {

    private UserOperations userOperations;

    private RepoOperations repoOperations;

    private PrivilegeOperations privilegeOperations;

    private UsersOperations usersOperations;

    public BitBucketTemplate(String consumerKey, String consumerSecret,
                             String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        initSubApis();
    }

    public BitBucketTemplate() {
        super();
        initSubApis();
    }

    private void initSubApis() {
        userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
        repoOperations = new RepoTemplate(getRestTemplate(), isAuthorized());
        privilegeOperations = new PrivilegeTemplate(getRestTemplate(),
                isAuthorized());
        usersOperations = new UsersTemplate(getRestTemplate(), isAuthorized());
    }

    @Override
    public final RepoOperations repoOperations() {
        return repoOperations;
    }

    @Override
    public final UserOperations userOperations() {
        return userOperations;
    }

    @Override
    public final PrivilegeOperations privelegesOperations() {
        return privilegeOperations;
    }

    @Override
    public final UsersOperations usersOperations() {
        return usersOperations;
    }
}
