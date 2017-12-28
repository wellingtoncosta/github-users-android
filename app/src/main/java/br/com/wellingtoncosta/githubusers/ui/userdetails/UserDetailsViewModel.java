package br.com.wellingtoncosta.githubusers.ui.userdetails;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.BaseScheduler;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserDetailsViewModel extends BaseViewModel<User> {

    private BaseScheduler scheduler;

    private UserRepository userRepository;

    @Inject
    public UserDetailsViewModel(BaseScheduler scheduler, UserRepository userRepository) {
        this.scheduler = scheduler;
        this.userRepository = userRepository;
    }

    public void loadUserDetails(String username) {
        userRepository.getUser(username)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        user -> response.setValue(Response.success(user)),
                        throwable -> response.setValue(Response.error(throwable))
                );
    }

}