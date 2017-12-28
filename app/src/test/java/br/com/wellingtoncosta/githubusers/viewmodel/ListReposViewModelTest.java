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
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.ui.userdetails.repos.ListReposViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.TestSchedulerProvider;
import io.reactivex.schedulers.TestScheduler;

import static br.com.wellingtoncosta.githubusers.Mocks.createRepos;
import static io.reactivex.Observable.just;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class ListReposViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    private RepoRepository repoRepository;

    private ListReposViewModel listReposViewModel;

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Before
    public void setUp(){
        repoRepository = mock(RepoRepository.class);

        listReposViewModel = new ListReposViewModel(new TestSchedulerProvider(new TestScheduler()), repoRepository);

        when(repoRepository.getRepos(USERNAME_TEST)).thenReturn(just(createRepos()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void empty() {
        Observer<Response<List<Repo>>> result = mock(Observer.class);
        listReposViewModel.getResponse().observeForever(result);
        verifyNoMoreInteractions(repoRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadReposByUserWithSucess() {
        Observer<Response<List<Repo>>> result = mock(Observer.class);
        listReposViewModel.getResponse().observeForever(result);
        listReposViewModel.loadRepos(USERNAME_TEST);
        verify(repoRepository).getRepos(USERNAME_TEST);
    }

}
