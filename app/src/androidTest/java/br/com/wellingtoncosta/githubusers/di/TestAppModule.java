package br.com.wellingtoncosta.githubusers.di;

import javax.inject.Singleton;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import br.com.wellingtoncosta.githubusers.di.modules.ViewModelModule;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.domain.repository.UserRepository;
import br.com.wellingtoncosta.githubusers.util.scheduler.BaseScheduler;
import br.com.wellingtoncosta.githubusers.util.scheduler.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

import static br.com.wellingtoncosta.githubusers.util.Mocks.createRepos;
import static br.com.wellingtoncosta.githubusers.util.Mocks.createStarredRepos;
import static br.com.wellingtoncosta.githubusers.util.Mocks.createUser;
import static br.com.wellingtoncosta.githubusers.util.Mocks.createUsers;
import static io.reactivex.Observable.just;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@Module(includes = ViewModelModule.class)
class TestAppModule {

    @Provides
    @Singleton
    GitHubApi provideApi() {
        GitHubApi api = mock(GitHubApi.class);

        when(api.getUsers()).thenReturn(just(createUsers()));
        when(api.getUser("WellingtonCosta")).thenReturn(just(createUser()));
        when(api.getRepos("WellingtonCosta", 1)).thenReturn(just(createRepos()));
        when(api.getRepos("WellingtonCosta", 2)).thenReturn(just(emptyList()));
        when(api.getStarredRepos("WellingtonCosta", 1)).thenReturn(just(createStarredRepos()));
        when(api.getStarredRepos("WellingtonCosta", 2)).thenReturn(just(emptyList()));

        return api;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(GitHubApi api) {
        return new UserRepository(api);
    }

    @Provides
    @Singleton
    RepoRepository provideRepoRepository(GitHubApi api) {
        return new RepoRepository(api);
    }

    @Provides
    @Singleton
    BaseScheduler provideScheduler() {
        return new SchedulerProvider();
    }

}