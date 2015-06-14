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
package org.springframework.social.bitbucket.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * Implementation of {@link ApiHelper} for BitBucket, used to retrieve an
 * instance of {@link BitBucket}.
 *
 * @author Eric Bottard
 */
public final class BitBucketApiHelper implements ApiHelper<BitBucket> {
    private final UsersConnectionRepository usersConnectionRepository;

    private final UserIdSource userIdSource;

    private BitBucketApiHelper(
            UsersConnectionRepository usersConnectionRepository,
            UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    @Override
    public BitBucket getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for BitBucket");
        }

        Connection<BitBucket> connection = usersConnectionRepository
                .createConnectionRepository(userIdSource.getUserId())
                .findPrimaryConnection(BitBucket.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default BitBucketTemplate instance.");
            return null;
        }
        return connection.getApi();
    }

    private final Log logger = LogFactory.getLog(BitBucketApiHelper.class);
}
