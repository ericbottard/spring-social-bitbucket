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
import org.springframework.util.StringUtils;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2Page<T> {

    @JsonProperty("pagelen")
    private int pageLength;

    @JsonProperty("values")
    private List<T> values;

    @JsonProperty
    private int page;

    @JsonProperty
    private int size;

    @JsonProperty
    private String next;

    @JsonProperty
    private String previous;

    /**
     * Current number of objects on the existing page. Globally, the minimum length is 10 and the maximum is 100. Some
     * APIs may specify a different default.
     */
    public final int getPageLength() {
        return pageLength;
    }

    /**
     * The list of objects. This contains at most pageLength objects.
     */
    public final List<T> getValues() {
        return values;
    }

    /**
     * Page number of the current results. This is an optional element that is not provided in all responses.
     */
    public final int getPage() {
        return page;
    }

    /**
     * Total number of objects in the response. This is an optional element that is not provided in all responses, as it
     * can be expensive to compute.
     */
    public final int getSize() {
        return size;
    }

    /**
     * Link toe previous page if it exists. A collections first page does not have this value. This is an optional
     * element that is not provided in all responses. Some result sets strictly support forward navigation and never
     * provide previous links. Clients must anticipate that backwards navigation is not always available.
     * <p/>
     * Use this link to navigate the result set and refrain from constructing your own URLs.
     */
    public final String getNext() {
        return next;
    }

    /**
     * Link to the next page if it exists. The last page of a collection does not have this value. Use this link to
     * navigate the result set and refrain from constructing your own URLs.
     */
    public final String getPrevious() {
        return previous;
    }

    /**
     * @return true if this page has a next page defined.
     */
    public final boolean hasNext() {
        return StringUtils.hasText(next);
    }

    /**
     * @return true if this page has a previous page defiend.
     */
    public final boolean hasPrevious() {
        return StringUtils.hasText(previous);
    }
}
