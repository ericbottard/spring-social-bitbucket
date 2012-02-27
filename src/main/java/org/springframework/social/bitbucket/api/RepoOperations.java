package org.springframework.social.bitbucket.api;

import java.util.List;
import java.util.Map;

/**
 * BitBucket sub-API that allows interaction with code repositories.
 * 
 * @author ericbottard
 */
public interface RepoOperations {

    /**
     * Returns the public repository that belongs to {@code user} and that has
     * {@code repoSlug} as its technical name.
     */
    public BitBucketRepository getRepository(String user, String repoSlug);

    /**
     * Returns the list of repositories for the current user.
     */
    public List<BitBucketRepository> getUserRepositories();

    /**
     * Search for public repositories whose name contains the given String.
     */
    public List<BitBucketRepository> search(String string);

    /**
     * Returns a mapping between tags (keys) and changesets (values) on the
     * given repository.
     */
    public Map<String, BitBucketChangeset> getTags(String user, String repoSlug);

    /**
     * Returns the list of users following the given repository.
     */
    public List<BitBucketUser> getFollowers(String user, String repoSlug);

    /**
     * Returns the last changesets on a repository, as well as information about
     * how many are available totally.
     */
    public BitBucketChangesets getChangesets(String user, String repoSlug);

    /**
     * Returns some changesets on a repository, as well as information about how
     * many are available totally.
     */
    public BitBucketChangesets getChangesets(String user, String repoSlug,
            String start, int limit);
}
