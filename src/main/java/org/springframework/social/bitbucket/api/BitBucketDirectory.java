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
package org.springframework.social.bitbucket.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Metadata about the contents of a repository directory. Contains files,
 * directories and metadata about the selected directory.
 * 
 * @author ericbottard
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

    public List<String> getDirectories() {
        return directories;
    }

    public List<BitBucketFileMetadata> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public String getNode() {
        return node;
    }

}
