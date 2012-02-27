package org.springframework.social.bitbucket.connect;

import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.bitbucket.api.impl.BitBucketTemplate;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;

public class BitBucketServiceProvider extends
        AbstractOAuth1ServiceProvider<BitBucket> {

    public BitBucketServiceProvider(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret, new OAuth1Template(consumerKey,
                consumerSecret,
                "https://api.bitbucket.org/1.0/oauth/request_token/",
                "https://api.bitbucket.org/1.0/oauth/authorize/",
                "https://api.bitbucket.org/1.0/oauth/authenticate/",
                "https://bitbucket.org/api/1.0/oauth/access_token/"));
    }

    @Override
    public BitBucket getApi(String accessToken, String secret) {
        return new BitBucketTemplate(getConsumerKey(), getConsumerSecret(),
                accessToken, secret);
    }

}
