package br.com.wellingtoncosta.githubusers.domain.repository;

import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import io.reactivex.Observable;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserRepository {

    private GitHubApi api;

    @Inject
    public UserRepository(GitHubApi api) {
        this.api = api;
    }

    public Observable<List<User>> getUsers() {
        return api.getUsers();
    }

    public Observable<User> getUser(String username) {
        return api.getUser(username);
    }

    public Observable<List<User>> getFollowers(String username) {
        return api.getFollowers(username);
    }

    public Observable<List<User>> getFollowing(String username) {
        return api.getFollowing(username);
    }

}