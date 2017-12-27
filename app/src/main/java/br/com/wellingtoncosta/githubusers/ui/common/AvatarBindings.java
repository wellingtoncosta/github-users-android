package br.com.wellingtoncosta.githubusers.ui.common;

import android.databinding.BindingAdapter;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import br.com.wellingtoncosta.githubusers.domain.model.User;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class AvatarBindings {

    @BindingAdapter("load_user_avatar")
    public static void loadUserAvatar(SimpleDraweeView simpleDraweeView, User user) {
        if (user != null) {
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
            roundingParams.setRoundAsCircle(true);

            simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
            simpleDraweeView.setImageURI(user.getAvatarUrl());
        }
    }

}
