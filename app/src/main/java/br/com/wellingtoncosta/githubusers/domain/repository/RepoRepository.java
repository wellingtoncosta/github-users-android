package br.com.wellingtoncosta.githubusers.domain.repository;

import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import io.reactivex.Observable;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class RepoRepository {

    private GitHubApi api;

    @Inject
    public RepoRepository(GitHubApi api) {
        this.api = api;
    }

    public Observable<List<Repo>> getRepos(String username) {
        return api.getRepos(username);
    }

    public Observable<List<Repo>> getStarredRepos(String username) {
        return api.getStarredRepos(username);
    }

}