package org.springframework.social.bitbucket.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.social.test.client.RequestMatchers.*;
import static org.springframework.social.test.client.ResponseCreators.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketRepository;
import org.springframework.social.bitbucket.api.BitBucketSCM;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.bitbucket.api.UserWithRepositories;

public class UserTemplateTest extends BaseTemplateTest {

    @Test
    public void testGetUser() throws Exception {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/user"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("get-user"), responseHeaders));

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
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/users/cleonello/followers"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("user-followers"),
                                responseHeaders));

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