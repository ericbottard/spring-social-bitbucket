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
package org.springframework.social.bitbucket.api.v2.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Every 2.0 object contains a links element that points to related resources or alternate representations. Use links to
 * quickly discover and traverse to related objects. Links serve a "self-documenting" function for each endpoint.
 *
 * @link https://confluence.atlassian.com/display/BITBUCKET/Version+2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

    @JsonProperty
    private String href;

    @JsonProperty
    private String name;

    @JsonProperty
    private String title;

    @JsonProperty
    private boolean templated;

    public final String getHref() {
        return href;
    }

    public final String getName() {
        return name;
    }

    public final String getTitle() {
        return title;
    }

    /**
     * Links can support URI Templates; Those that do contain a "templated": "true" element.
     *
     * @link http://tools.ietf.org/html/rfc6570
     */
    public final boolean isTemplated() {
        return templated;
    }
}
