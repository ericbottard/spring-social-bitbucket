/**
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.bitbucket.security;

import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.connect.BitBucketConnectionFactory;
import org.springframework.social.security.provider.OAuth1AuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * {@link SocialAuthenticationService} implementation for BitBucket.
 * 
 * @author Eric Bottard
 */
public class BitBucketAuthenticationService extends
        OAuth1AuthenticationService<BitBucket> {

    public BitBucketAuthenticationService(String apiKey, String appSecret) {
        super(new BitBucketConnectionFactory(apiKey, appSecret));
    }

}
