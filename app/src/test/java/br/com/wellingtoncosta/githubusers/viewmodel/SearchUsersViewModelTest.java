package br.com.wellingtoncosta.githubusers.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.ui.searchuser.SearchUsersViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.TestSchedulerProvider;
import io.reactivex.schedulers.TestScheduler;

import static br.com.wellingtoncosta.githubusers.Mocks.createUsers;
import static io.reactivex.Observable.just;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class SearchUsersViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    private UserRepository userRepository;

    private SearchUsersViewModel searchUsersViewModel;

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Before
    public void setUp(){
        userRepository = mock(UserRepository.class);

        searchUsersViewModel = new SearchUsersViewModel(new TestSchedulerProvider(new TestScheduler()), userRepository);

        when(userRepository.getUsers()).thenReturn(just(createUsers()));
        when(userRepository.getUser(USERNAME_TEST)).thenReturn(just(new User()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void empty() {
        Observer<Response<List<User>>> result = mock(Observer.class);
        searchUsersViewModel.getResponse().observeForever(result);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadUsersWithSuccess() {
        Observer<Response<List<User>>> result = mock(Observer.class);
        searchUsersViewModel.getResponse().observeForever(result);
        searchUsersViewModel.loadUsers();
        verify(userRepository).getUsers();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadUserByUsernameWithSuccess() {
        Observer<Response<List<User>>> result = mock(Observer.class);
        searchUsersViewModel.getResponse().observeForever(result);
        searchUsersViewModel.loadUser(USERNAME_TEST);
        verify(userRepository).getUser(USERNAME_TEST);
    }

}