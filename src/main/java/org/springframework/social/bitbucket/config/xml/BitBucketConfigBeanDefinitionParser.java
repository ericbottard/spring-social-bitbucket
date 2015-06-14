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
package org.springframework.social.bitbucket.config.xml;

import org.springframework.social.bitbucket.config.support.BitBucketApiHelper;
import org.springframework.social.bitbucket.connect.BitBucketConnectionFactory;
import org.springframework.social.bitbucket.security.BitBucketAuthenticationService;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * Implementation of {@link AbstractProviderConfigBeanDefinitionParser} that
 * creates a {@link BitBucketConnectionFactory}.
 *
 * @author Eric Bottard
 */
public class BitBucketConfigBeanDefinitionParser extends
        AbstractProviderConfigBeanDefinitionParser {

    public BitBucketConfigBeanDefinitionParser() {
        super(BitBucketConnectionFactory.class, BitBucketApiHelper.class);
    }

    @Override
    protected final Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return BitBucketAuthenticationService.class;
    }
}
