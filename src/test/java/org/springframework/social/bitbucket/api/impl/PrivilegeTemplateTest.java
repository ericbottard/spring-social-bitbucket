package org.springframework.social.bitbucket.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.bitbucket.api.BitBucketPrivilege;
import org.springframework.social.bitbucket.api.RepoPrivilege;

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
