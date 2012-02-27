package org.springframework.social.bitbucket.api;

import java.util.List;

/**
 * BitBucket sub-API that allows interaction with user accounts.
 * 
 * @author ericbottard
 */
public interface UserOperations {

    /**
     * Returns information about the current user, as well as the list of
     * repositories he/she owns.
     */
    UserWithRepositories getUserWithRepositories();

    /**
     * Returns the list of users that follow the given {@code user}.
     */
    List<BitBucketUser> getFollowers(String user);

}
