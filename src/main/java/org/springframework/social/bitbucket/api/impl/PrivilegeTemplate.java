package org.springframework.social.bitbucket.api.impl;

import static java.util.Arrays.*;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.bitbucket.api.BitBucketPrivilege;
import org.springframework.social.bitbucket.api.PrivilegeOperations;
import org.springframework.social.bitbucket.api.RepoPrivilege;
import org.springframework.web.client.RestTemplate;

public class PrivilegeTemplate extends AbstractBitBucketOperations implements
        PrivilegeOperations {

    public PrivilegeTemplate(RestTemplate restTemplate, boolean authorized) {
        super(restTemplate, authorized);
    }

    @Override
    public List<RepoPrivilege> getRepoPrivileges(String user, String repoSlug) {
        return asList(restTemplate.getForObject(
                buildUrl("/privileges/{user}/{repo_slug}"),
                RepoPrivilege[].class, user, repoSlug));
    }

    @Override
    public RepoPrivilege setPrivilege(String owner, String repoSlug,
            String recipient, BitBucketPrivilege privilege) {

        return restTemplate.exchange(
                buildUrl("/privileges/{user}/{repo_slug}/{recipient}"),
                HttpMethod.PUT, new HttpEntity<String>(privilege.toString()),
                RepoPrivilege[].class, owner, repoSlug, recipient).getBody()[0];
    }

    @Override
    public void removePrivilege(String owner, String repoSlug, String recipient) {
        restTemplate.delete(
                buildUrl("/privileges/{user}/{repo_slug}/{recipient}"), owner,
                repoSlug, recipient);
    }

}
