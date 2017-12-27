package br.com.wellingtoncosta.githubusers.ui.details.repos;

import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class ListReposViewModel extends BaseViewModel<List<Repo>> {

    private RepoRepository repoRepository;

    @Inject
    ListReposViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    void loadRepos(String username) {
        repoRepository.getRepos(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        user -> response.setValue(Response.success(user)),
                        throwable -> response.setValue(Response.error())
                );
    }

}