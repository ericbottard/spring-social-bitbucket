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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2Account {

    @JsonProperty
    private String username;

    @JsonProperty
    private BitBucketV2Kind kind;

    @JsonProperty("website")
    private String website;

    @JsonProperty("display_name")
    private String displayName;

    // links

    // created on

    @JsonProperty
    private String location;

    public final String getUsername() {
        return username;
    }

    public final BitBucketV2Kind getKind() {
        return kind;
    }

    public final String getWebsite() {
        return website;
    }

    public final String getDisplayName() {
        return displayName;
    }

    public final String getLocation() {
        return location;
    }
}
