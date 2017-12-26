package br.com.wellingtoncosta.githubusers.di.modules;

import javax.inject.Singleton;

import br.com.wellingtoncosta.githubusers.data.remote.GitHubApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author Wellington Costa on 26/12/2017.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    GitHubApi provideApi(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

}