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
package org.springframework.social.bitbucket.api.v2.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

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

    private static final Map<String, BitBucketForkPolicy> NAME_TO_POLICY =
            ImmutableMap.<String, BitBucketForkPolicy>builder()
                    .put("allow_forks", allowForks)
                    .put("no_public_forks", noPublicForks)
                    .put("no_forks", noForks)
                    .build();

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
        BitBucketForkPolicy forkPolicy = NAME_TO_POLICY.get(value);
        if (forkPolicy != null) {
            return forkPolicy;
        }
        throw new IllegalArgumentException(format("Could not deserialize [%s]", value));
    }
}
