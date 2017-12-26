package br.com.wellingtoncosta.githubusers.ui.search;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import br.com.wellingtoncosta.githubusers.domain.model.User;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersBindings {

    @BindingAdapter("app:load_users")
    public static void loadUsers(RecyclerView recyclerView, List<User> users) {
        SearchUsersAdapter adapter = new SearchUsersAdapter(users);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("app:load_user_avatar")
    public static void loadUserAvatar(SimpleDraweeView simpleDraweeView, User user) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);

        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        simpleDraweeView.setImageURI(user.getAvatarUrl());
    }

}
