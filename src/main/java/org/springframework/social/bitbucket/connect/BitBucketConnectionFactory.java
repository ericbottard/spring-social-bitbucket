package org.springframework.social.bitbucket.connect;

import org.springframework.social.bitbucket.api.BitBucket;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;

public class BitBucketConnectionFactory extends
        OAuth1ConnectionFactory<BitBucket> {

    public BitBucketConnectionFactory(String consumerKey, String consumerSecret) {
        super("bitbucket", new BitBucketServiceProvider(consumerKey,
                consumerSecret), new BitBucketAdapter());
    }
}
