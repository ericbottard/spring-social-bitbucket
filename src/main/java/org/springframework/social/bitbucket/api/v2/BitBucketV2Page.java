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
package org.springframework.social.bitbucket.api.v2;

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

    public int getPageLength() {
        return pageLength;
    }

    public List<T> getValues() {
        return values;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public boolean hasNext() {
        return StringUtils.hasText(next);
    }

    public boolean hasPrevious() {
        return StringUtils.hasText(previous);
    }
}
