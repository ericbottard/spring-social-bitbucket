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

import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.api.impl.BitBucketTemplate;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;

public class BitBucketServiceProvider extends
        AbstractOAuth1ServiceProvider<BitBucket> {

    public BitBucketServiceProvider(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret, new OAuth1Template(consumerKey,
                consumerSecret,
                "https://bitbucket.org/!api/1.0/oauth/request_token",
                "https://bitbucket.org/!api/1.0/oauth/authenticate",
                "https://bitbucket.org/!api/1.0/oauth/access_token"));
    }

    @Override
    public BitBucket getApi(String accessToken, String secret) {
        return new BitBucketTemplate(getConsumerKey(), getConsumerSecret(),
                accessToken, secret);
    }

}
