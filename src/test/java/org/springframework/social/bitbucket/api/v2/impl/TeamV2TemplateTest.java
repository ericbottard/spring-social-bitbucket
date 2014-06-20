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
package org.springframework.social.bitbucket.api.v2.impl;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketSCM;
import org.springframework.social.bitbucket.api.v2.payload.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class TeamV2TemplateTest extends BaseV2TemplateTest {

    @Test
    public void testGetTeam() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/teams/1team"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-team"),
                                MediaType.APPLICATION_JSON));

        BitBucketV2Account team = bitBucket.getTeamOperations().getTeam("1team");
        assertEquals("1team", team.getUsername());
        assertEquals(BitBucketV2Kind.team, team.getKind());
        assertEquals("", team.getWebsite());
        assertEquals("the team", team.getDisplayName());
        assertEquals("", team.getLocation());
    }

    @Test
    public void testGetTeamMembers() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/teams/1team/members"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-team-members"),
                                MediaType.APPLICATION_JSON));

        AccountPage page = bitBucket.getTeamOperations().getMembers("1team");
        assertEquals(50, page.getPageLength());
        assertEquals(1, page.getPage());
        assertEquals(1, page.getSize());
        assertEquals(1, page.getValues().size());
        BitBucketV2Account user = page.getValues().get(0);
        assertEquals("tutorials", user.getUsername());
        assertEquals("https://tutorials.bitbucket.org/", user.getWebsite());
        assertEquals("first name last", user.getDisplayName());
        assertEquals("Santa Monica, CA", user.getLocation());
    }

    @Test
    public void testGetTeamFollowers() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/teams/1team/followers"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-team-followers"),
                                MediaType.APPLICATION_JSON));

        AccountPage page = bitBucket.getTeamOperations().getFollowers("1team");
        assertEquals(10, page.getPageLength());
        assertEquals(1, page.getPage());
        assertEquals(1, page.getSize());
        assertEquals(1, page.getValues().size());
        BitBucketV2Account user = page.getValues().get(0);
        assertEquals("tutorials", user.getUsername());
        assertEquals(BitBucketV2Kind.team, user.getKind());
        assertEquals("https://tutorials.bitbucket.org/", user.getWebsite());
        assertEquals("first name last", user.getDisplayName());
        assertEquals("Santa Monica, CA", user.getLocation());
    }

    @Test
    public void testGetTeamFollowing() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/teams/1team/following"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-team-following"),
                                MediaType.APPLICATION_JSON));

        AccountPage page = bitBucket.getTeamOperations().getFollowing("1team");
        assertEquals(10, page.getPageLength());
        assertEquals(1, page.getPage());
        assertEquals(1, page.getSize());
        assertEquals(1, page.getValues().size());
        BitBucketV2Account user = page.getValues().get(0);
        assertEquals("bitbucket", user.getUsername());
        assertEquals(BitBucketV2Kind.team, user.getKind());
        assertEquals("http://bitbucket.org/", user.getWebsite());
        assertEquals("Atlassian Bitbucket", user.getDisplayName());
        assertEquals("", user.getLocation());
    }

    @Test
    public void testGetTeamRepositories() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/teams/1team/repositories"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-team-repositories"),
                                MediaType.APPLICATION_JSON));

        RepositoryPage page = bitBucket.getTeamOperations().getRepositories("1team");
        assertEquals(10, page.getPageLength());
        assertEquals(1, page.getPage());
        assertEquals(4, page.getSize());
        assertEquals(4, page.getValues().size());
        BitBucketV2Repository repository = page.getValues().get(0);
        assertEquals(BitBucketSCM.git, repository.getScm());
        assertEquals(false, repository.hasWiki());
        assertEquals("", repository.getDescription());
        assertEquals(BitBucketForkPolicy.allowForks, repository.getForkPolicy());
        assertEquals("java", repository.getLanguage());
        assertEquals("1team/myarfx", repository.getFullName());
        assertEquals(false, repository.hasIssues());
        assertEquals(203173, repository.getSize());
        assertEquals(false, repository.isPrivate());
        assertEquals("MyArfX", repository.getName());
    }

}
