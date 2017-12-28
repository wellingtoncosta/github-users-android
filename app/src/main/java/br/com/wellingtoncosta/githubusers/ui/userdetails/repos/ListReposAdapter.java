package br.com.wellingtoncosta.githubusers.ui.userdetails.repos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.databinding.ListReposItemBinding;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.ui.base.BaseAdapter;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class ListReposAdapter extends BaseAdapter<Repo> {

    ListReposAdapter() {
        super();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType) {
        return new ListReposViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_repos_item, parent, false));
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position) {
        ListReposItemBinding binding = ((ListReposViewHolder)holder).getBinding();
        Repo repo = list.get(position);
        binding.setRepo(repo);
    }

}