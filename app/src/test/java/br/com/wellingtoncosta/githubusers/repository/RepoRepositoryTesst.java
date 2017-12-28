package br.com.wellingtoncosta.githubusers.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;

import static br.com.wellingtoncosta.githubusers.Mocks.createRepos;
import static br.com.wellingtoncosta.githubusers.Mocks.createStarredRepos;
import static io.reactivex.Observable.just;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class RepoRepositoryTesst {

    @Mock
    private GitHubApi api;

    @InjectMocks
    private RepoRepository repoRepository;

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        when(repoRepository.getRepos(USERNAME_TEST)).thenReturn(just(createRepos()));
        when(repoRepository.getStarredRepos(USERNAME_TEST)).thenReturn(just(createStarredRepos()));
    }

    @Test
    public void getReposByUsernameWithSuccess() {
        repoRepository.getRepos(USERNAME_TEST);
        verify(api).getRepos(USERNAME_TEST);
    }

    @Test
    public void getStarredReposByUsernameWithSuccess() {
        repoRepository.getStarredRepos(USERNAME_TEST);
        verify(api).getStarredRepos(USERNAME_TEST);
    }

}
