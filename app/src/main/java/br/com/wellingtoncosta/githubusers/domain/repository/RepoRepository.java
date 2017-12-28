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

    public Observable<List<Repo>> getRepos(String username, int page) {
        return api.getRepos(username, page);
    }

    public Observable<List<Repo>> getStarredRepos(String username, int page) {
        return api.getStarredRepos(username, page);
    }

}