/**
 * Copyright (C) 2012 Eric Bottard (eric.bottard+ghpublic@gmail.com)
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

import java.util.List;

public interface PrivilegeOperations {

    /**
     * Get the privileges on a given repository.
     *
     * @param user     repository owner
     * @param repoSlug name of the repository
     */
    List<RepoPrivilege> getRepoPrivileges(String user, String repoSlug);

    /**
     * Set or change the privilege for the given recipient on some repository.
     *
     * @param owner     the repository owner
     * @param repoSlug  the repository name
     * @param recipient user for which to set the privilege
     * @param privilege new privilege value to set
     */
    RepoPrivilege setPrivilege(String owner, String repoSlug,
                               String recipient, BitBucketPrivilege privilege);

    /**
     * Removes the current privilege of the given recipient from some
     * repository.
     *
     * @param owner     the repository owner
     * @param repoSlug  the repository name
     * @param recipient user for which to remove the privilege
     */
    void removePrivilege(String owner, String repoSlug, String recipient);

}
