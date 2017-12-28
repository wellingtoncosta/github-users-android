package br.com.wellingtoncosta.githubusers.ui.searchuser;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.wellingtoncosta.githubusers.domain.model.User;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersBindings {

    @BindingAdapter("load_users")
    public static void loadUsers(RecyclerView recyclerView, List<User> users) {
        if (users != null) {
            SearchUsersAdapter adapter = (SearchUsersAdapter) recyclerView.getAdapter();
            adapter.getList().clear();
            adapter.getList().addAll(users);
            adapter.notifyDataSetChanged();
        }
    }

}
