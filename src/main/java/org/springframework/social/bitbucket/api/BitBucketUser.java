package org.springframework.social.bitbucket.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A BitBucket user account.
 * 
 * @author ericbottard
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("avatar")
    private String avatarImageUrl;

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

}
