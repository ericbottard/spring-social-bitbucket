/**
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.bitbucket.api.v2.payload;

import com.fasterxml.jackson.annotation.JsonCreator;

import static java.lang.String.format;

public enum BitBucketForkPolicy {

    /**
     * unrestricted forking
     */
    allowForks,
    /**
     * restrict forking to private forks (forks cannot be made public later)
     */
    noPublicForks,
    /**
     * deny all forking
     */
    noForks;

    /**
     * Helper method for deserialization.
     *
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static BitBucketForkPolicy forValue(String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("allow_forks")) {
            return allowForks;
        } else if (value.equals("no_public_forks")) {
            return noPublicForks;
        } else if (value.equals("no_forks")) {
            return noForks;
        }
        throw new IllegalArgumentException(format("Could not deserialize [%s]", value));
    }
}
