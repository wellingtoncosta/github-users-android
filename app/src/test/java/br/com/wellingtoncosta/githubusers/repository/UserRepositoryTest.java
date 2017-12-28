package br.com.wellingtoncosta.githubusers.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;

import static br.com.wellingtoncosta.githubusers.Mocks.createUsers;
import static io.reactivex.Observable.just;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class UserRepositoryTest {

    @Mock
    private GitHubApi api;

    @InjectMocks
    private UserRepository userRepository;

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        when(userRepository.getUsers()).thenReturn(just(createUsers()));
        when(userRepository.getUser(USERNAME_TEST)).thenReturn(just(new User()));
    }

    @Test
    public void getUsersWithSuccess() {
        userRepository.getUsers();
        verify(api).getUsers();
    }

    @Test
    public void getUserByUsernameWithSuccess() {
        userRepository.getUser(USERNAME_TEST);
        verify(api).getUser(USERNAME_TEST);
    }

}