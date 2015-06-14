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
package org.springframework.social.bitbucket.connect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.social.ApiException;
import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.api.BitBucketEmailAddress;
import org.springframework.social.bitbucket.api.BitBucketUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public class BitBucketAdapter implements ApiAdapter<BitBucket> {

    private final Log logger = LogFactory.getLog(BitBucketAdapter.class);

    @Override
    public final boolean test(BitBucket api) {
        try {
            return null != api.userOperations().getUserWithRepositories();
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public final void setConnectionValues(BitBucket api, ConnectionValues values) {
        BitBucketUser user = api.userOperations().getUserWithRepositories()
                .getUser();
        values.setImageUrl(user.getAvatarImageUrl());
        values.setProviderUserId(user.getUsername());
        values.setDisplayName(user.getFirstName() + " " + user.getLastName());
        values.setProfileUrl("https://bitbucket.org/" + user.getUsername());
    }

    @Override
    public final UserProfile fetchUserProfile(BitBucket api) {
        BitBucketUser user = api.userOperations().getUserWithRepositories().getUser();
        String primaryEmail = fetchPrimaryEmailIfAvailableForUserProfile(api, user);
        return new UserProfileBuilder().setFirstName(user.getFirstName())
                .setLastName(user.getLastName()).setEmail(primaryEmail)
                .setUsername(user.getUsername()).build();
    }

    private String fetchPrimaryEmailIfAvailableForUserProfile(BitBucket api, BitBucketUser user) {
        try {
            List<BitBucketEmailAddress> emailAddresses = api.usersOperations()
                    .emailsOperations().getListOfUserEmailAddresses(user.getUsername());
            for (BitBucketEmailAddress emailAddress : emailAddresses) {
                if (emailAddress.getPrimary()) {
                    return emailAddress.getEmail();
                }
            }
        } catch (HttpClientErrorException clientError) {
            if (!HttpStatus.FORBIDDEN.equals(clientError.getStatusCode())) {
                throw clientError;
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("BitBucket consumer configuration: Permissions:Acount:Email is not granted."
                            + " Email property will be set to 'null' in UserProfile"
                            + " [username:'" + user.getUsername() + "']");
                }
            }
        }
        return null;
    }

    @Override
    public void updateStatus(BitBucket api, String message) {
        // NOOP
    }

}
