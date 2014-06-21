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
package org.springframework.social.bitbucket.api;

import org.springframework.social.ApiBinding;

/**
 * Main interface for interacting with BitBucket.
 *
 * @author ericbottard
 */
public interface BitBucket extends ApiBinding {

    /**
     * Returns the portion of the BitBucket API that allow interaction with
     * repositories.
     */
    RepoOperations repoOperations();

    /**
     * Returns the portion of the BitBucket API that allow interaction with user
     * accounts.
     */
    UserOperations userOperations();

    /**
     * Returns the portion of the BitBucket API that allow messing with
     * privileges.
     */
    PrivilegeOperations privelegesOperations();
}
