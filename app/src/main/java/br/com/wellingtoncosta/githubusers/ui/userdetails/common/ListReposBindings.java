package br.com.wellingtoncosta.githubusers.ui.userdetails.common;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

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
        if (repos != null) {
            ListReposAdapter adapter = (ListReposAdapter) recyclerView.getAdapter();
            adapter.getList().addAll(repos);
            adapter.notifyDataSetChanged();
        }
    }

    @BindingAdapter("load_starred_repos")
    public static void loadStarredRepos(RecyclerView recyclerView, List<Repo> starredRepos) {
        if (starredRepos != null) {
            ListStarredReposAdapter adapter = (ListStarredReposAdapter) recyclerView.getAdapter();
            adapter.getList().addAll(starredRepos);
            adapter.notifyDataSetChanged();
        }
    }

}