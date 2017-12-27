package br.com.wellingtoncosta.githubusers.ui.details;

import java.util.Collections;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.UserDetails;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserDetailsViewModel extends BaseViewModel<UserDetails> {

    private UserRepository userRepository;

    private RepoRepository repoRepository;

    @Inject
    UserDetailsViewModel(UserRepository userRepository, RepoRepository repoRepository) {
        this.userRepository = userRepository;
        this.repoRepository = repoRepository;
    }

    void loadUserDetails(String username) {
        userRepository.getUser(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        user -> response.setValue(Response.success(new UserDetails(user, Collections.emptyList()))),
                        throwable -> response.setValue(Response.error())
                );
    }

}