package br.com.wellingtoncosta.githubusers.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import br.com.wellingtoncosta.githubusers.di.keys.ViewModelKey;
import br.com.wellingtoncosta.githubusers.ui.common.ViewModelFactory;
import br.com.wellingtoncosta.githubusers.ui.userdetails.UserDetailsViewModel;
import br.com.wellingtoncosta.githubusers.ui.userdetails.repos.ListReposViewModel;
import br.com.wellingtoncosta.githubusers.ui.userdetails.starredrepos.ListStarredReposViewModel;
import br.com.wellingtoncosta.githubusers.ui.searchuser.SearchUsersViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Wellington Costa on 26/12/2017.
 */
@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchUsersViewModel.class)
    ViewModel bindSearchUsersViewModel(SearchUsersViewModel searchUsersViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel.class)
    ViewModel bindUserDetailsViewModel(UserDetailsViewModel userDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListReposViewModel.class)
    ViewModel bindListRepositoriesViewModel(ListReposViewModel listReposViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListStarredReposViewModel.class)
    ViewModel bindListStarsViewModel(ListStarredReposViewModel listStarredReposViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}