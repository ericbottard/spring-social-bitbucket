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
import org.springframework.social.bitbucket.api.BitBucketPrivilege;
import org.springframework.social.bitbucket.api.RepoPrivilege;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class PrivilegeTemplateTest extends BaseTemplateTest {
    @Test
    public void testGetRepoPrivileges() {

        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/privileges/evzijst/test"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-repo-privileges"),
                                MediaType.APPLICATION_JSON));

        List<RepoPrivilege> privs = bitBucket.privelegesOperations()
                .getRepoPrivileges("evzijst", "test");

        assertEquals(4, privs.size());
        assertEquals("evzijst/test", privs.get(0).getRepository());
        assertEquals("jespern", privs.get(0).getUser().getUsername());
        assertEquals(BitBucketPrivilege.read, privs.get(0).getPrivilege());

    }

    @Test
    public void testDeletePrivilege() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/privileges/evzijst/test/jespern"))
                .andExpect(method(DELETE)).andRespond(withNoContent());

        bitBucket.privelegesOperations().removePrivilege("evzijst", "test",
                "jespern");
    }

    @Test
    public void testSetPrivilege() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/1.0/privileges/tutorials/mydvcsproject/atlassian_tutorial"))
                .andExpect(method(PUT))
                .andExpect(content().string("write"))
                .andRespond(
                        withSuccess(jsonResource("set-repo-privilege"),
                                MediaType.APPLICATION_JSON));

        RepoPrivilege priv = bitBucket.privelegesOperations().setPrivilege(
                "tutorials", "mydvcsproject", "atlassian_tutorial",
                BitBucketPrivilege.write);

        assertEquals("Atlassian Tutorials", priv.getUser().getFirstName());
    }
}
