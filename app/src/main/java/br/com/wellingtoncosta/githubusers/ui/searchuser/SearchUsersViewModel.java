package br.com.wellingtoncosta.githubusers.ui.searchuser;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersViewModel extends BaseViewModel<List<User>> {

    private UserRepository repository;

    @Inject
    SearchUsersViewModel(UserRepository repository) {
        this.repository = repository;
    }

    void loadUsers() {
        repository.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        users -> response.setValue(Response.success(users)),
                        throwable -> response.setValue(Response.error(throwable))
                );
    }

    void loadUser(String username) {
        repository.getUser(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        user -> response.setValue(Response.success(Collections.singletonList(user))),
                        throwable -> response.setValue(Response.error(throwable))
                );
    }

}