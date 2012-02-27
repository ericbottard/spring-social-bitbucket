package org.springframework.social.bitbucket.api.impl;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.social.test.client.MockRestServiceServer;

public class BaseTemplateTest {

    protected BitBucketTemplate bitBucket;
    protected MockRestServiceServer mockServer;

    public BaseTemplateTest() {
        super();
    }

    @Before
    public void setUp() {

        bitBucket = new BitBucketTemplate("consumerKey", "consumerSecret",
                "accessToken", "accessTokenSecret");
        mockServer = MockRestServiceServer.createServer(bitBucket
                .getRestTemplate());

    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

}