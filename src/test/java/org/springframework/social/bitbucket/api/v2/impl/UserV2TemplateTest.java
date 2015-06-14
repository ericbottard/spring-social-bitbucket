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
import org.springframework.social.bitbucket.api.v2.payload.BitBucketV2Account;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class UserV2TemplateTest extends BaseV2TemplateTest {

    @Test
    public void testGetUser() {
        mockServer
                .expect(requestTo("https://api.bitbucket.org/2.0/users/tutorials"))
                .andExpect(method(GET))
                .andRespond(
                        withSuccess(jsonResource("get-user"),
                                MediaType.APPLICATION_JSON));

        BitBucketV2Account team = bitBucket.getUserOperations().getUser("tutorials");
        assertEquals("tutorials", team.getUsername());
    }

}
