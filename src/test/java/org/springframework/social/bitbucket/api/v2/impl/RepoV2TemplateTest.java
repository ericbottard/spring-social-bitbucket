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

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketSCM;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketForkPolicy;
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Repository;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RepoV2TemplateTest extends BaseV2TemplateTest {

    @Test
    public void testGetRepositories() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/repositories"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-all-public-repositories"),
                                MediaType.APPLICATION_JSON));

        RepositoryPage repos = bitBucket.getRepositoriesOperations().getRepositories();
        assertEquals(1, repos.getPage());
        assertEquals(10, repos.getPageLength());
        assertEquals(1, repos.getValues().size());
        BitBucketV2Repository repository = repos.getValues().get(0);
        assertEquals(BitBucketSCM.git, repository.getScm());
        assertEquals(false, repository.hasWiki());
        assertEquals("", repository.getDescription());
        assertEquals(BitBucketForkPolicy.allowForks, repository.getForkPolicy());
        assertEquals("", repository.getLanguage());
        assertEquals("evzijst/atlassian-connect-fork", repository.getFullName());
        assertEquals(false, repository.hasIssues());
        assertEquals(17657351, repository.getSize());
        assertEquals(false, repository.isPrivate());
        assertEquals("atlassian-connect-fork", repository.getName());
        assertEquals("evzijst", repository.getOwner().getUsername());
        assertEquals("Erik van Zijst", repository.getOwner().getDisplayName());
    }

    @Test
    public void testGetRepositoriesForUser() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/repositories/evzijst"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-repositories-for-account"),
                                MediaType.APPLICATION_JSON));

        RepositoryPage repos = bitBucket.getRepositoriesOperations().getRepositories("evzijst");
        assertEquals(1, repos.getPage());
        assertEquals(10, repos.getPageLength());
        assertEquals(1, repos.getValues().size());
        BitBucketV2Repository repository = repos.getValues().get(0);
        assertEquals(BitBucketSCM.git, repository.getScm());
        assertEquals(false, repository.hasWiki());
        assertEquals("", repository.getDescription());
        assertEquals(BitBucketForkPolicy.allowForks, repository.getForkPolicy());
        assertEquals("", repository.getLanguage());
        assertEquals("evzijst/atlassian-connect-fork", repository.getFullName());
        assertEquals(false, repository.hasIssues());
        assertEquals(17657351, repository.getSize());
        assertEquals(false, repository.isPrivate());
        assertEquals("atlassian-connect-fork", repository.getName());
        assertEquals("evzijst", repository.getOwner().getUsername());
        assertEquals("Erik van Zijst", repository.getOwner().getDisplayName());
    }

}
