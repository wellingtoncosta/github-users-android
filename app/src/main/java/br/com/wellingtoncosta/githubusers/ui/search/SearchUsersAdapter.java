package br.com.wellingtoncosta.githubusers.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.databinding.ListUsersItemBinding;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.ui.base.BaseAdapter;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersAdapter extends BaseAdapter<User> {

    SearchUsersAdapter(List<User> users) {
        this.list = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType) {
        return new SearchUsersViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_users_item, parent, false));
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position) {
        ListUsersItemBinding binding = ((SearchUsersViewHolder)holder).getBinding();
        User user = list.get(position);
        binding.setUser(user);
    }

}