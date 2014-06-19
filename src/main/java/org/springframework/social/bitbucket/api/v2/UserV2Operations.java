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
package org.springframework.social.bitbucket.api.v2;

import org.springframework.social.bitbucket.api.v2.payload.AccountPage;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;

public interface UserV2Operations {

    BitBucketV2Account getUser(String username);

    AccountPage getFollowers(String username);

    AccountPage getFollowers(BitBucketV2Account user);

    AccountPage getFollowing(String username);

    AccountPage getFollowing(BitBucketV2Account user);

    RepositoryPage getRepositories(String username);

    RepositoryPage getRepositories(BitBucketV2Account user);

    AccountPage getNextPage(AccountPage page);

    RepositoryPage getNextPage(RepositoryPage page);

}
