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
package org.springframework.social.bitbucket.api.v2.impl;

import org.springframework.social.bitbucket.api.impl.AbstractBitBucketOperations;
import org.springframework.social.bitbucket.api.v2.PageV2Operations;
import org.springframework.social.bitbucket.api.v2.payload.AccountPage;
import org.springframework.social.bitbucket.api.v2.payload.PullRequestPage;
import org.springframework.social.bitbucket.api.v2.payload.RepositoryPage;
import org.springframework.web.client.RestTemplate;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.springframework.social.bitbucket.api.v2.impl.BitBucketV2Template.*;

public class PageV2Template extends AbstractBitBucketOperations implements PageV2Operations {

    public PageV2Template(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized, V2);
    }

    @Override
    public final AccountPage getNextPage(AccountPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_NEXT_PAGE);
        return getRestTemplate().getForObject(
                page.getNext(),
                AccountPage.class);
    }

    @Override
    public final PullRequestPage getNextPage(PullRequestPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_NEXT_PAGE);
        return getRestTemplate().getForObject(
                page.getNext(),
                PullRequestPage.class);
    }

    @Override
    public final RepositoryPage getNextPage(RepositoryPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_NEXT_PAGE);
        return getRestTemplate().getForObject(
                page.getNext(),
                RepositoryPage.class);
    }

    @Override
    public final AccountPage getPreviousPage(AccountPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_PREVIOUS_PAGE);
        return getRestTemplate().getForObject(
                page.getPrevious(),
                AccountPage.class);
    }

    @Override

    public final PullRequestPage getPreviousPage(PullRequestPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_PREVIOUS_PAGE);
        return getRestTemplate().getForObject(
                page.getPrevious(),
                PullRequestPage.class);
    }

    @Override
    public final RepositoryPage getPreviousPage(RepositoryPage page) {
        checkNotNull(page, PAGE_IS_REQUIRED);
        checkState(page.hasNext(), PAGE_DOES_NOT_HAVE_A_PREVIOUS_PAGE);
        return getRestTemplate().getForObject(
                page.getPrevious(),
                RepositoryPage.class);
    }

}
