/**
 * Copyright (C) 2012 Eric Bottard (eric.bottard+ghpublic@gmail.com)
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

import org.springframework.web.client.RestTemplate;

public class AbstractBitBucketOperations {

    private final boolean authorized;

    protected final RestTemplate restTemplate;

    private final String version;

    public static final String V1 = "1.0";
    public static final String V2 = "2.0";

    private static final String BASE_API_URL = "https://api.bitbucket.org/";

    public AbstractBitBucketOperations(RestTemplate restTemplate,
                                       boolean authorized, String version) {
        this.restTemplate = restTemplate;
        this.authorized = authorized;
        this.version = version;
    }

    protected final String buildUrl(String string) {
        return BASE_API_URL + version + string;
    }

    protected final boolean isAuthorized() {
        return authorized;
    }

    protected final RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
