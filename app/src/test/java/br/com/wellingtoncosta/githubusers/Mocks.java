package br.com.wellingtoncosta.githubusers;

import java.util.ArrayList;
import java.util.List;

import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.model.User;

/**
 * @author Wellington Costa on 28/12/2017.
 */
public class Mocks {

    public static List<User> createUsers() {
        List<User> mockedUsers = new ArrayList<>();

        mockedUsers.add(new User());
        mockedUsers.add(new User());
        mockedUsers.add(new User());
        mockedUsers.add(new User());
        mockedUsers.add(new User());

        return mockedUsers;
    }

    public static List<Repo>createRepos() {
        List<Repo> reposMock = new ArrayList<>();

        reposMock.add(new Repo());
        reposMock.add(new Repo());
        reposMock.add(new Repo());
        reposMock.add(new Repo());
        reposMock.add(new Repo());

        return reposMock;
    }

    public static List<Repo> createStarredRepos() {
        List<Repo> starredReposMock = new ArrayList<>();

        starredReposMock.add(new Repo());
        starredReposMock.add(new Repo());
        starredReposMock.add(new Repo());
        starredReposMock.add(new Repo());
        starredReposMock.add(new Repo());

        return starredReposMock;
    }

}