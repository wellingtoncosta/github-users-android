package br.com.wellingtoncosta.githubusers.domain.model;

import java.util.List;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserDetails {

    public final User user;

    public final List<Repo> repos;

    public UserDetails(User user, List<Repo> repos) {
        this.user = user;
        this.repos = repos;
    }

}