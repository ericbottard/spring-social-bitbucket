package org.springframework.social.bitbucket.api;

import java.util.List;

public interface PrivilegeOperations {

    /**
     * Get the privileges on a given repository.
     * 
     * @param user
     *            repository owner
     * @param repoSlug
     *            name of the repository
     */
    public List<RepoPrivilege> getRepoPrivileges(String user, String repoSlug);

    /**
     * Set or change the privilege for the given recipient on some repository.
     * 
     * @param owner
     *            the repository owner
     * @param repoSlug
     *            the repository name
     * @param recipient
     *            user for which to set the privilege
     * @param privilege
     *            new privilege value to set
     */
    public RepoPrivilege setPrivilege(String owner, String repoSlug,
            String recipient, BitBucketPrivilege privilege);

    /**
     * Removes the current privilege of the given recipient from some
     * repository.
     * 
     * @param owner
     *            the repository owner
     * @param repoSlug
     *            the repository name
     * @param recipient
     *            user for which to remove the privilege
     */
    public void removePrivilege(String owner, String repoSlug, String recipient);

}
