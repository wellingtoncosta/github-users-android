package br.com.wellingtoncosta.githubusers.di.modules;

import br.com.wellingtoncosta.githubusers.ui.details.repos.ListReposFragment;
import br.com.wellingtoncosta.githubusers.ui.details.starreds.ListStarredReposFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Wellington Costa on 27/12/2017.
 */
@Module
public interface FragmentBuildersModule {

    @ContributesAndroidInjector
    ListReposFragment contributeListRepositoriesFragment();

    @ContributesAndroidInjector
    ListStarredReposFragment contributeListStarredReposFragment();

}