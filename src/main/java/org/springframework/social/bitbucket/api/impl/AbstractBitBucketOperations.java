package org.springframework.social.bitbucket.api.impl;

public class AbstractBitBucketOperations {

    protected final boolean authorized;

    private static final String BASE_API_URL = "https://api.bitbucket.org/1.0";

    public AbstractBitBucketOperations(boolean authorized) {
        this.authorized = authorized;
    }

    protected String buildUrl(String string) {
        return BASE_API_URL + string;
    }

}