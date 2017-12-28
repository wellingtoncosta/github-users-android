package br.com.wellingtoncosta.githubusers.ui.userdetails.common;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.ui.userdetails.repos.ListReposAdapter;
import br.com.wellingtoncosta.githubusers.ui.userdetails.starredrepos.ListStarredReposAdapter;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class ListReposBindings {

    @BindingAdapter("load_repos")
    public static void loadRepos(RecyclerView recyclerView, List<Repo> repos) {
        if (repos != null && repos.size() > 0) {
            ListReposAdapter adapter = (ListReposAdapter) recyclerView.getAdapter();
            List<Repo> newRepos = new LinkedList<>();

            newRepos.addAll(adapter.getList());
            newRepos.addAll(repos);

            adapter.setList(newRepos);
            adapter.notifyDataSetChanged();
        }
    }

    @BindingAdapter("load_starred_repos")
    public static void loadStarredRepos(RecyclerView recyclerView, List<Repo> starredRepos) {
        if (starredRepos != null && starredRepos.size() > 0) {
            ListStarredReposAdapter adapter = (ListStarredReposAdapter) recyclerView.getAdapter();
            List<Repo> newStarredRepos = new LinkedList<>();

            newStarredRepos.addAll(adapter.getList());
            newStarredRepos.addAll(starredRepos);

            adapter.setList(newStarredRepos);
            adapter.notifyDataSetChanged();
        }
    }

}