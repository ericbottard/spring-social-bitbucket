package org.springframework.social.bitbucket.config.annotation;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.social.bitbucket.config.support.BitBucketApiHelper;
import org.springframework.social.bitbucket.connect.BitBucketConnectionFactory;
import org.springframework.social.bitbucket.security.BitBucketAuthenticationService;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * {@link ImportBeanDefinitionRegistrar} for configuring a
 * {@link TwitterConnectionFactory} bean and a request-scoped {@link Twitter}
 * bean.
 * 
 * @author Craig Walls
 */
public class TwitterProviderConfigRegistrar extends
        AbstractProviderConfigRegistrarSupport {

    public TwitterProviderConfigRegistrar() {
        super(EnableBitBucket.class, BitBucketConnectionFactory.class,
                BitBucketApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return BitBucketAuthenticationService.class;
    }

}