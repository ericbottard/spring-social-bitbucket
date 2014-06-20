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
package org.springframework.social.bitbucket.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.bitbucket.api.impl.UTCDateDeserializer;

import java.util.Date;

/**
 * Metadata about a file in a repository.
 *
 * @author Eric Bottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketFileMetadata {

    @JsonProperty
    private String path;

    @JsonProperty
    private String revision;

    @JsonProperty("utctimestamp")
    @JsonDeserialize(using = UTCDateDeserializer.class)
    private Date timestamp;

    @JsonProperty
    private int size;

    public String getPath() {
        return path;
    }

    public String getRevision() {
        return revision;
    }

    public Date getTimestamp() {
        return (Date) timestamp.clone();
    }

    public int getSize() {
        return size;
    }
}
