package br.com.wellingtoncosta.githubusers.ui.searchuser;

import android.view.View;

import br.com.wellingtoncosta.githubusers.databinding.ListUsersItemBinding;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewHolder;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersViewHolder extends BaseViewHolder<ListUsersItemBinding> {

    SearchUsersViewHolder(View view) {
        super(view);
    }

    public interface OnItemClickListener {
        void click(User user);
    }

}