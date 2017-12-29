package br.com.wellingtoncosta.githubusers.ui.common;

import android.databinding.BindingAdapter;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import br.com.wellingtoncosta.githubusers.R;
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

            if (user.getAvatarUrl() != null) {
                simpleDraweeView.setImageURI(user.getAvatarUrl());
            } else {
                ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.ic_account).build();
                simpleDraweeView.setImageURI(imageRequest.getSourceUri());
            }
        }
    }

}
