package org.springframework.social.bitbucket.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.social.test.client.RequestMatchers.*;
import static org.springframework.social.test.client.ResponseCreators.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketChangeset;
import org.springframework.social.bitbucket.api.BitBucketChangeset.FileModificationType;
import org.springframework.social.bitbucket.api.BitBucketChangesets;
import org.springframework.social.bitbucket.api.BitBucketRepository;
import org.springframework.social.bitbucket.api.BitBucketSCM;
import org.springframework.social.bitbucket.api.BitBucketUser;

public class RepoTemplateTest extends BaseTemplateTest {

    @Test
    public void testGetOneRepo() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/jespen/django-piston/"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("get-one-repo"),
                                responseHeaders));

        BitBucketRepository repo = bitBucket.repoOperations().getRepository(
                "jespen", "django-piston");
        assertEquals(BitBucketSCM.hg, repo.getScm());
        assertEquals("jespern", repo.getOwner());
        assertEquals("django-piston", repo.getName());
        assertEquals("django-piston", repo.getSlug());
        assertEquals("Piston is a Django mini-framework creating APIs.",
                repo.getDescription());

    }

    @Test
    public void testGetUserRepositories() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/user/repositories/"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("get-user-repositories"),
                                responseHeaders));

        List<BitBucketRepository> repos = bitBucket.repoOperations()
                .getUserRepositories();

        assertEquals(3, repos.size());
        BitBucketRepository first = repos.iterator().next();
        assertEquals("ericbottard", first.getOwner());
        assertEquals(BitBucketSCM.git, first.getScm());
        assertEquals("mahjong", first.getSlug());
        assertTrue(first.isPrivate());
        assertEquals("mahjong", first.getName());
    }

    @Test
    public void testSearchForRepos() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/?name=box2d"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("search-repos"),
                                responseHeaders));

        List<BitBucketRepository> results = bitBucket.repoOperations().search(
                "box2d");
        assertEquals(6, results.size());
        BitBucketRepository first = results.get(0);
        assertEquals(BitBucketSCM.hg, first.getScm());
        assertEquals("Box2DXNA", first.getName());
        assertEquals("fuzzybinary", first.getOwner());
        assertEquals("box2dxna", first.getSlug());
        assertEquals(
                "Personal version of Box2DX that is being used in AngelXNA.",
                first.getDescription());

    }

    @Test
    public void testRepoTags() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/tags/"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("get-tags"), responseHeaders));

        Map<String, BitBucketChangeset> tags = bitBucket.repoOperations()
                .getTags("tortoisehg", "thg");
        assertEquals(68, tags.size());

        BitBucketChangeset tag = tags.get("0.9");
        assertEquals("400e85fa0b84", tag.getNode());
        assertEquals("400e85fa0b8442af109d00081e8840359f095a67",
                tag.getRawNode());
        assertEquals("Steve Borho <steve@borho.org>", tag.getRawAuthor());
        assertEquals("sborho", tag.getAuthor());
        assertEquals("stable", tag.getBranch());
        assertEquals("i18n: pull final Ukranian translation for 0.9",
                tag.getMessage());
        assertEquals(4922, tag.getRevision());
        assertEquals(Arrays.asList("e6bec1d7925b"), tag.getParents());
        assertEquals(1, tag.getFiles().size());
        assertEquals("i18n/tortoisehg/uk.po", tag.getFiles().get(0).getFile());
        assertEquals(FileModificationType.modified, tag.getFiles().get(0)
                .getType());

    }

    @Test
    public void testRepoFollowers() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/followers/"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("repo-followers"),
                                responseHeaders));

        List<BitBucketUser> followers = bitBucket.repoOperations()
                .getFollowers("tortoisehg", "thg");
        assertEquals(290, followers.size());
        BitBucketUser first = followers.get(0);
        assertEquals(
                "https://secure.gravatar.com/avatar/fa7ba4c8ca1f7d725f0f1bc4f2d470c2?d=identicon&s=32",
                first.getAvatarImageUrl());
        assertEquals("Simon", first.getFirstName());
        assertEquals("H.", first.getLastName());
        assertEquals("simohe", first.getUsername());

    }

    @Test
    public void testRepoChangesets() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/changesets/"))
                .andExpect(method(GET))
                .andRespond(
                        withResponse(jsonResource("repo-changesets"),
                                responseHeaders));

        BitBucketChangesets changesets = bitBucket.repoOperations()
                .getChangesets("tortoisehg", "thg");

        assertEquals(15, changesets.getLimit());
        assertEquals(12594, changesets.getCount());

        BitBucketChangeset changeset = changesets.getChangesets().get(0);

        assertEquals("dbfb47ecca2d", changeset.getNode());
        assertEquals("dbfb47ecca2d742e148f0c0f58682f41c5f8ef85",
                changeset.getRawNode());
        assertEquals("Yuya Nishihara <yuya@tcha.org>", changeset.getRawAuthor());
        assertEquals("yuja", changeset.getAuthor());
        assertEquals("stable", changeset.getBranch());
        assertEquals(
                "manifestmodel: suppress extra _buildrootentry on initial setNameFilter('')",
                changeset.getMessage());
        assertEquals(12579, changeset.getRevision());
        assertEquals(Arrays.asList("4230823499de"), changeset.getParents());
        assertEquals(1, changeset.getFiles().size());
        assertEquals("tortoisehg/hgqt/manifestmodel.py", changeset.getFiles()
                .get(0).getFile());
        assertEquals(FileModificationType.modified, changeset.getFiles().get(0)
                .getType());

    }

}
