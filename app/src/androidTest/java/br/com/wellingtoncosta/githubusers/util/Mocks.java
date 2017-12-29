package br.com.wellingtoncosta.githubusers.util;

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

        mockedUsers.add(new User(
                1L,
                null,
                "Wellington Costa",
                "WellingtonCosta",
                "Fortaleza")
        );

        mockedUsers.add(new User(
                2L,
                null,
                "Jake Wharton",
                "JakeWharton",
                "Pittsburg")
        );

        mockedUsers.add(new User(
                3L,
                null,
                "Linus Torvalds",
                "torvalds",
                "Portland")
        );

        mockedUsers.add(new User(
                4L,
                null,
                "Rudson Lima",
                "rudsonlive",
                "Fortaleza")
        );

        mockedUsers.add(new User(
                5L,
                null,
                "Wellington Costa",
                "WellingtonCosta",
                "Fortaleza")
        );

        return mockedUsers;
    }

    public static User createUser() {
        return new User();
    }

    public static List<Repo>createRepos() {
        List<Repo> reposMock = new ArrayList<>();

        reposMock.add(new Repo(
                1L,
                "convalida",
                "WellingtonCosta/convalida",
                "Java",
                32L,
                6L
        ));
        reposMock.add(new Repo(
                2L,
                "github-users-android",
                "WellingtonCosta/github-users-android",
                "Java",
                0L,
                0L
        ));
        reposMock.add(new Repo(
                3L,
                "android-mvvm-databinding",
                "WellingtonCosta/android-mvvm-databinding",
                "Java",
                0L,
                0L
        ));
        reposMock.add(new Repo(
                4L,
                "contactcs-api",
                "WellingtonCosta/contactcs-api",
                "Java",
                32L,
                6L
        ));
        reposMock.add(new Repo(
                5L,
                "my-movies",
                "WellingtonCosta/my-movies",
                "Java",
                1L,
                1L
        ));

        return reposMock;
    }

    public static List<Repo> createStarredRepos() {
        List<Repo> starredReposMock = new ArrayList<>();

        starredReposMock.add(new Repo(
                1L,
                "repo-1",
                "foo/repo-1",
                "Java",
                0L,
                0L
        ));
        starredReposMock.add(new Repo(
                2L,
                "repo-2",
                "foo/repo-2",
                "Java",
                0L,
                0L
        ));
        starredReposMock.add(new Repo(
                3L,
                "repo-3",
                "foo/repo-3",
                "Java",
                0L,
                0L
        ));
        starredReposMock.add(new Repo(
                4L,
                "repo-4",
                "foo/repo-4",
                "Java",
                0L,
                0L
        ));
        starredReposMock.add(new Repo(
                5L,
                "repo-5",
                "foo/repo-5",
                "Java",
                0L,
                0L
        ));

        return starredReposMock;
    }

}