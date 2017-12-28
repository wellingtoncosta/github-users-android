package br.com.wellingtoncosta.githubusers.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.ui.userdetails.UserDetailsViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.TestSchedulerProvider;
import io.reactivex.schedulers.TestScheduler;

import static io.reactivex.Observable.just;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class UserDetailsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    private UserRepository userRepository;

    private UserDetailsViewModel userDetailsViewModel;

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Before
    public void setUp(){
        userRepository = mock(UserRepository.class);

        userDetailsViewModel = new UserDetailsViewModel(new TestSchedulerProvider(new TestScheduler()), userRepository);

        when(userRepository.getUser(USERNAME_TEST)).thenReturn(just(new User()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void empty() {
        Observer<Response<User>> result = mock(Observer.class);
        userDetailsViewModel.getResponse().observeForever(result);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadUserByUsernameWithSuccess() {
        Observer<Response<User>> result = mock(Observer.class);
        userDetailsViewModel.getResponse().observeForever(result);
        userDetailsViewModel.loadUserDetails(USERNAME_TEST);
        verify(userRepository).getUser(USERNAME_TEST);
    }

}