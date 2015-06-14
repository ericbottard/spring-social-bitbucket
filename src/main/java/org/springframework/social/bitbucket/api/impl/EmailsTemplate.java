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

import org.springframework.social.bitbucket.api.BitBucketEmailAddress;
import org.springframework.social.bitbucket.api.EmailsOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Cyprian Śniegota
 * @since 2.0.0
 */
public class EmailsTemplate extends AbstractBitBucketOperations implements EmailsOperations {

    public EmailsTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V1);
    }

    @Override
    public final List<BitBucketEmailAddress> getListOfUserEmailAddresses(String accountName) {
        return Arrays.asList(getRestTemplate().getForObject(buildUrl("/users/{accountname}/emails"),
                BitBucketEmailAddress[].class, accountName));
    }

    @Override
    public final BitBucketEmailAddress getAnEmailAddress(String accountName, String emailAddress) {
        return getRestTemplate().getForObject(buildUrl("/users/{accountname}/emails/{email_address}"),
                BitBucketEmailAddress.class, accountName, emailAddress);
    }

    @Override
    public final List<BitBucketEmailAddress> postANewEmailAddress(String accountName, String emailAddress) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public final BitBucketEmailAddress updateAnEmailAddress(String accountName, String emailAddress) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
