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
package org.springframework.social.bitbucket.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Metadata about the contents of a repository directory. Contains files,
 * directories and metadata about the selected directory.
 *
 * @author Eric Bottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketDirectory {

    @JsonProperty
    private List<String> directories;

    @JsonProperty
    private List<BitBucketFileMetadata> files;

    @JsonProperty
    private String path;

    @JsonProperty
    private String node;

    public final List<String> getDirectories() {
        return directories;
    }

    public final List<BitBucketFileMetadata> getFiles() {
        return files;
    }

    public final String getPath() {
        return path;
    }

    public final String getNode() {
        return node;
    }

}
