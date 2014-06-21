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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.bitbucket.api.BitBucketPrivilege;
import org.springframework.social.bitbucket.api.PrivilegeOperations;
import org.springframework.social.bitbucket.api.RepoPrivilege;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;

public class PrivilegeTemplate extends AbstractBitBucketOperations implements
        PrivilegeOperations {

    public PrivilegeTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V1);
    }

    @Override
    public List<RepoPrivilege> getRepoPrivileges(String user, String repoSlug) {
        return asList(restTemplate.getForObject(
                buildUrl("/privileges/{user}/{repo_slug}"),
                RepoPrivilege[].class, user, repoSlug));
    }

    @Override
    public RepoPrivilege setPrivilege(String owner, String repoSlug,
                                      String recipient, BitBucketPrivilege privilege) {

        return restTemplate.exchange(
                buildUrl("/privileges/{user}/{repo_slug}/{recipient}"),
                HttpMethod.PUT, new HttpEntity<String>(privilege.toString()),
                RepoPrivilege[].class, owner, repoSlug, recipient).getBody()[0];
    }

    @Override
    public void removePrivilege(String owner, String repoSlug, String recipient) {
        restTemplate.delete(
                buildUrl("/privileges/{user}/{repo_slug}/{recipient}"), owner,
                repoSlug, recipient);
    }

}
