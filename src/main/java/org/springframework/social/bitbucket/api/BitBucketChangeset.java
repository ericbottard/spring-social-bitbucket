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
import lombok.Getter;

import java.util.List;

/**
 * A changeset, <i>aka</i> commit.
 *
 * @author ericbottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketChangeset {

    /** The kind of modification on a file path. */
    public enum FileModificationType {
        modified, added, removed
    }

    /** The username of the {@link BitBucketUser} that made the change. */
    @JsonProperty @Getter
    private String author;

    /** The name of the branch where the commit occurred. */
    @JsonProperty @Getter
    private String branch;

    /** A list of affected files. */
    @JsonProperty @Getter
    private List<FileModification> files;

    /** The commit message as entered by the {@link #getAuthor() author}. */
    @JsonProperty @Getter
    private String message;

    /** The short node hash. */
    @JsonProperty @Getter
    private String node;

    /** The parent commit(s) of this change. */
    @JsonProperty @Getter
    private List<String> parents;

    /** The raw author identifier of this change (includes email address). */
    @JsonProperty("raw_author") @Getter
    private String rawAuthor;

    /** The full node hash. */
    @JsonProperty("raw_node") @Getter
    private String rawNode;

    @JsonProperty @Getter
    private long revision;

    @Override
    public final String toString() {
        return getNode();
    }

    /**
     * A modification on a single file.
     *
     * @author ericbottard
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class FileModification {

        /** The path of the file (or directory) affected. */
        @JsonProperty @Getter
        private String file;

        /** The kind of modification. */
        @JsonProperty @Getter
        private FileModificationType type;

    }

}
