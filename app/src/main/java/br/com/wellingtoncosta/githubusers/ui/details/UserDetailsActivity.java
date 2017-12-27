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
import br.com.wellingtoncosta.githubusers.ui.common.ViewPagerAdapter;
import br.com.wellingtoncosta.githubusers.ui.details.repository.ListRepositoriesFragment;
import br.com.wellingtoncosta.githubusers.ui.details.star.ListStarsFragment;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class UserDetailsActivity extends BaseActivity<UserDetailsViewModel> {

    private ActivityUserDetailsBinding binding;

    @Override
    public void setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
        setupTabs();
    }

    private void setupTabs() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ListRepositoriesFragment(), getString(R.string.repositories));
        adapter.addFragment(new ListStarsFragment(), getString(R.string.stars));

        binding.viewPager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
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
                binding.setUser(response.data);
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