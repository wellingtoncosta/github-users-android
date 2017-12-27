package br.com.wellingtoncosta.githubusers.ui.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.Collections;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.ActivitySearchUsersBinding;
import br.com.wellingtoncosta.githubusers.ui.base.BaseActivity;
import br.com.wellingtoncosta.githubusers.ui.details.UserDetailsActivity;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersActivity extends BaseActivity<SearchUsersViewModel> {

    private ActivitySearchUsersBinding binding;

    private SearchUsersViewHolder.OnItemClickListener onItemClickListener = (userClicked) -> {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("username", userClicked.getUsername());
        startActivity(intent);
    };

    @Override
    public void setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_users);
        binding.includeContent.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.includeContent.recyclerView.setAdapter(new SearchUsersAdapter(onItemClickListener));
        binding.includeContent.swipeContainer.setOnRefreshListener(viewModel::loadUsers);
    }

    @Override
    public void setupViewModel() {
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(SearchUsersViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeLoadingStatus();
        observeResponse();
        viewModel.loadUsers();
    }

    public void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(
                this,
                isLoading  -> binding.includeContent.swipeContainer.setRefreshing(isLoading == null ? false : isLoading)
        );
    }

    public void observeResponse() {
        viewModel.getResponse().observe(this, response -> {
            if (response != null) {
                if(response.status == Status.SUCCESS) {
                    binding.includeContent.setUsers(response.data);
                    binding.executePendingBindings();
                } else {
                    binding.includeContent.setUsers(Collections.emptyList());
                    showLongSnackbar(binding.getRoot(), R.string.load_data_failure);
                    Log.e("get users error", response.throwable.getMessage());
                }
            }
        });
    }

}