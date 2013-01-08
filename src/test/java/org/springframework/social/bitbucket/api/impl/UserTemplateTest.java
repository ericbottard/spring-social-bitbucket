/**
 * Copyright 2012 the original author or authors.
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
package org.springframework.social.bitbucket.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketRepository;
import org.springframework.social.bitbucket.api.BitBucketSCM;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.UserWithRepositories;

public class UserTemplateTest extends BaseTemplateTest {

    @Test
    public void testGetUser() throws Exception {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/user"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-user"),
                                MediaType.APPLICATION_JSON));

        UserWithRepositories profile = bitBucket.userOperations()
                .getUserWithRepositories();

        assertEquals("ericbottard", profile.getUser().getUsername());
        assertEquals("Eric", profile.getUser().getFirstName());
        assertEquals("Bottard", profile.getUser().getLastName());
        assertEquals(
                "https://secure.gravatar.com/avatar/9adf9e4ee93fea30ea27b469cfa3bae8?d=identicon&s=32",
                profile.getUser().getAvatarImageUrl());

        assertEquals(3, profile.getRepositories().size());
        BitBucketRepository repo = profile.getRepositories().iterator().next();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        assertEquals(sdf.parse("2011-11-20 18:25:29"), repo.getCreatedAt());
        assertEquals("ericbottard", repo.getOwner());
        assertEquals(BitBucketSCM.git, repo.getScm());
        assertTrue(repo.isHasWiki());
        assertEquals(29397517L, repo.getSize());
        assertFalse(repo.isReadOnly());
        assertTrue(repo.isPrivate());
        assertEquals(sdf.parse("2012-02-20 21:29:16+00:00"),
                repo.getLastUpdatedOn());

    }

    @Test
    public void testGetUserFollowers() throws Exception {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/users/cleonello/followers"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("user-followers"),
                                MediaType.APPLICATION_JSON));

        List<BitBucketUser> followers = bitBucket.userOperations()
                .getFollowers("cleonello");
        assertEquals(11, followers.size());
        BitBucketUser follower = followers.get(0);

        assertEquals("tobami", follower.getUsername());
        assertEquals("Miquel", follower.getFirstName());
        assertEquals("Torres", follower.getLastName());
        assertEquals(
                "https://secure.gravatar.com/avatar/a804ab4bb149c5c612a087e75d2c656c?d=identicon&s=32",
                follower.getAvatarImageUrl());
    }
}
