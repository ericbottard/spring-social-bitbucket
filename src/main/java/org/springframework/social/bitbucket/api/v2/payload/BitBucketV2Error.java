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

import javax.annotation.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketV2Error extends RuntimeException {

    @JsonProperty
    private String message;

    @JsonProperty
    @Nullable
    private String detail;

    @JsonProperty
    @Nullable
    private String id;

    // fields

    /**
     * A short description of the problem. This element is always present. Its value may be localized.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * An optional detailed explanation of the failure. Its value may be localized.
     */
    public final String getDetail() {
        return detail;
    }

    /**
     * An optional unique error identifier that identifies the error in Bitbucket's logging system. If you feel you hit
     * a bug in an API and this field is provided, please mention it if you decide to contact support as it will greatly
     * help us narrow down the problem.
     */
    public final String getId() {
        return id;
    }
}
