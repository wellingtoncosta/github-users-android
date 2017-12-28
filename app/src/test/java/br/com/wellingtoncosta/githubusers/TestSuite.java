package br.com.wellingtoncosta.githubusers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.wellingtoncosta.githubusers.repository.RepoRepositoryTesst;
import br.com.wellingtoncosta.githubusers.repository.UserRepositoryTest;
import br.com.wellingtoncosta.githubusers.viewmodel.ListReposViewModelTest;
import br.com.wellingtoncosta.githubusers.viewmodel.ListStarredReposViewModelTest;
import br.com.wellingtoncosta.githubusers.viewmodel.SearchUsersViewModelTest;
import br.com.wellingtoncosta.githubusers.viewmodel.UserDetailsViewModelTest;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(Suite.class)
@SuiteClasses({
        UserRepositoryTest.class,
        RepoRepositoryTesst.class,
        SearchUsersViewModelTest.class,
        UserDetailsViewModelTest.class,
        ListReposViewModelTest.class,
        ListStarredReposViewModelTest.class
})
public class TestSuite { }