package org.springframework.social.bitbucket.api.impl;

import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.api.RepoOperations;
import org.springframework.social.bitbucket.api.UserOperations;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class BitBucketTemplate extends AbstractOAuth1ApiBinding implements
        BitBucket {

    private UserOperations userOperations;

    private RepoOperations repoOperations;

    public BitBucketTemplate(String consumerKey, String consumerSecret,
            String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        initSubApis();
    }

    public BitBucketTemplate() {
        super();
        initSubApis();
    }

    private void initSubApis() {
        userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
        repoOperations = new RepoTemplate(getRestTemplate(), isAuthorized());
    }

    @Override
    public RepoOperations repoOperations() {
        return repoOperations;
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

}
