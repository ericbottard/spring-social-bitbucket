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

import org.springframework.social.support.ParameterMap;

/**
 * Data object with information needed for repository creation.
 *
 * @author Eric Bottard
 */
public class RepoCreation extends ParameterMap {

    public RepoCreation(String name) {
        set("name", name);
    }

    /**
     * The name of the repository.
     */
    public String getName() {
        return getFirst("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    /**
     * Boolean specifying if the repository is private (true) or public (false).
     * The default is false.
     */
    public boolean isMakePrivate() {
        return Boolean.valueOf(getFirst("is_private"));
    }

    public void setMakePrivate(boolean makePrivate) {
        set("is_private", Boolean.toString(makePrivate));
    }

    /**
     * A description of the repository.
     */
    public String getDescription() {
        return getFirst("description");
    }

    public void setDescription(String description) {
        set("description", description);
    }

    /**
     * A value of git or hg. The default is git if you leave this parameter
     * unspecified.
     */
    public BitBucketSCM getScm() {
        return BitBucketSCM.valueOf(getFirst("scm"));
    }

    public void setScm(BitBucketSCM scm) {
        set("scm", scm.name());
    }

    /**
     * The language used for source code in the repository.
     */
    public String getLanguage() {
        return getFirst("language");
    }

    public void setLanguage(String language) {
        set("language", language);
    }

}
