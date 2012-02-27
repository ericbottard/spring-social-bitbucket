package org.springframework.social.bitbucket.api;

import org.springframework.social.ApiBinding;

/**
 * Main interface for interacting with BitBucket.
 * 
 * @author ericbottard
 * 
 */
public interface BitBucket extends ApiBinding {

    /**
     * Returns the portion of the BitBucket API that allow interaction with
     * repositories.
     */
    RepoOperations repoOperations();

    /**
     * Returns the portion of the BitBucket API that allow interaction with user
     * accounts.
     */
    UserOperations userOperations();
}
