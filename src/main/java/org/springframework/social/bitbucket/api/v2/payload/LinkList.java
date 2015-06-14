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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkList {

    @JsonProperty
    private Link watchers;

    @JsonProperty
    private Link commits;

    @JsonProperty
    private Link self;

    @JsonProperty
    private Link html;

    @JsonProperty
    private Link avatar;

    @JsonProperty
    private Link forks;

    @JsonProperty
    private List<Link> clone;

    @JsonProperty
    private Link pullrequests;

    public final Link getWatchers() {
        return watchers;
    }

    public final Link getCommits() {
        return commits;
    }

    public final Link getSelf() {
        return self;
    }

    public final Link getHtml() {
        return html;
    }

    public final Link getAvatar() {
        return avatar;
    }

    public final Link getForks() {
        return forks;
    }

    public final List<Link> getClone() {
        return clone;
    }

    public final Link getPullrequests() {
        return pullrequests;
    }
}
