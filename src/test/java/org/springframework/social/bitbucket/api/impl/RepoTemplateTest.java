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

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.*;
import org.springframework.social.bitbucket.api.BitBucketChangeset.FileModificationType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RepoTemplateTest extends BaseTemplateTest {

    @Test
    public void testGetOneRepo() {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/jespen/django-piston/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-one-repo"),
                                MediaType.APPLICATION_JSON));

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

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/user/repositories/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-user-repositories"),
                                MediaType.APPLICATION_JSON));

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

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/?name=box2d"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("search-repos"),
                                MediaType.APPLICATION_JSON));

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

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/tags/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-tags"),
                                MediaType.APPLICATION_JSON));

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

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/followers/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("repo-followers"),
                                MediaType.APPLICATION_JSON));

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

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/tortoisehg/thg/changesets/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("repo-changesets"),
                                MediaType.APPLICATION_JSON));

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

    @Test
    public void testRepoDirectoryListing() {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/jespern/django-piston/src/tip/piston/"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("repo-directories"),
                                MediaType.APPLICATION_JSON));

        BitBucketDirectory directory = bitBucket.repoOperations().getDirectory(
                "jespern", "django-piston", "tip", "piston");

        assertThat(directory.getNode(), equalTo("4fe8af1db59d"));
        assertThat(directory.getPath(), equalTo("piston/"));

        assertThat(directory.getDirectories().size(), equalTo(2));
        assertThat(directory.getDirectories(),
                hasItems("fixtures", "templates"));

        BitBucketFileMetadata metadata = directory.getFiles().get(0);
        assertThat(metadata.getPath(), equalTo("piston/utils.py"));
        assertThat(metadata.getRevision(), equalTo("112311f7d7ce"));
        assertThat(metadata.getSize(), equalTo(12275));

    }

    @Test
    public void testRepoFile() {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories/jespern/django-piston/src/tip/piston/utils.py"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("repo-file"),
                                MediaType.APPLICATION_JSON));

        BitBucketFile file = bitBucket.repoOperations().getFile("jespern",
                "django-piston", "tip", "piston/utils.py");

        assertThat(file.getNode(), equalTo("4fe8af1db59d"));
        assertThat(file.getPath(), equalTo("piston/utils.py"));
        assertThat(file.getData().length(), equalTo(12275));
        // The following makes sure that newline escapes are correcly handled
        assertThat(
                file.getData(),
                startsWith("import time\nfrom django.http import HttpResponseNotAllowed,"));

    }

    @Test
    public void testRepoCreation() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/repositories"))
                .andExpect(method(POST))
                .andExpect(content().string("name=mynewrepo"))
                .andRespond(
                        withSuccess(jsonResource("create-repo"),
                                MediaType.APPLICATION_JSON));

        RepoCreation options = new RepoCreation("mynewrepo");
        BitBucketRepository repository = bitBucket.repoOperations()
                .createRepository(options);

        assertThat(repository.getName(), equalTo("mynewrepo"));
    }
}
