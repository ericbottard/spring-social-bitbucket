package org.springframework.social.bitbucket.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A container class that holds many {@link BitBucketChangeset changesets} as
 * well as information about total number of changesets available.
 * 
 * @author ericbottard
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketChangesets {

    @JsonProperty
    private List<BitBucketChangeset> changesets;

    @JsonProperty
    private int limit;

    @JsonProperty
    private String start;

    @JsonProperty
    private int count;

    public List<BitBucketChangeset> getChangesets() {
        return changesets;
    }

    public int getLimit() {
        return limit;
    }

    public String getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }
}
