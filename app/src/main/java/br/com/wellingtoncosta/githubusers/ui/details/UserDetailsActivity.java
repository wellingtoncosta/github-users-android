package br.com.wellingtoncosta.githubusers.ui.details;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.ActivityUserDetailsBinding;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import br.com.wellingtoncosta.githubusers.ui.base.BaseActivity;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserDetailsActivity extends BaseActivity<UserDetailsViewModel> {

    private ActivityUserDetailsBinding binding;

    @Override
    public void setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
    }

    @Override
    public void setupViewModel() {
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserDetailsViewModel.class);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeResponse();

        if(getIntent() != null) {
            viewModel.loadUserDetails(getIntent().getStringExtra("username"));
        }
    }

    public void observeResponse() {
        viewModel.getResponse().observe(this, response -> {
            if(response != null && response.status == Status.SUCCESS) {
                Log.d("user details", new Gson().toJson(response.data));
                binding.setUser(response.data.user);
                binding.setOnBackButtonClickListener(this::finish);
                binding.executePendingBindings();
            } else {
                binding.setUser(new User());
                showLongSnackbar(binding.getRoot(), R.string.load_data_failure);
            }
        });
    }

    public interface OnBackButtonClickListener {
        void click();
    }

}