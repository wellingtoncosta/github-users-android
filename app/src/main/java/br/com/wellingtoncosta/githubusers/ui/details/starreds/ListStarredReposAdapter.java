package br.com.wellingtoncosta.githubusers.ui.details.starreds;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.databinding.ListStarredReposItemBinding;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.ui.base.BaseAdapter;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class ListStarredReposAdapter extends BaseAdapter<Repo> {

    ListStarredReposAdapter() {
        super();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType) {
        return new ListStarredReposViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_starred_repos_item, parent, false));
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position) {
        ListStarredReposItemBinding binding = ((ListStarredReposViewHolder)holder).getBinding();
        Repo repo = list.get(position);
        binding.setRepo(repo);
    }

}
