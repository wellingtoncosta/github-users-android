package br.com.wellingtoncosta.githubusers.ui.search;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Collections;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.ActivitySearchBinding;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private SearchUsersViewModel viewModel;

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchUsersViewModel.class);

        observeLoadingStatus();
        observeResponse();

        viewModel.loadData();

        setupViews();
    }

    private void setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.swipeContainer.setOnRefreshListener(viewModel::loadData);
        setupToolbar();
    }

    private void setupToolbar() {
        if (binding.includeToolbar.toolbar != null) {
            setSupportActionBar(binding.includeToolbar.toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(getString(R.string.search_users));
            }
        }
    }

    public void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(
                this,
                isLoading  -> binding.swipeContainer.setRefreshing(isLoading == null ? false : isLoading)
        );
    }

    public void observeResponse() {
        viewModel.getResponse().observe(this, response -> {
            if(response != null && response.status == Status.SUCCESS) {
                binding.setUsers(response.data);
                binding.executePendingBindings();
            } else {
                binding.setUsers(Collections.emptyList());
                Snackbar.make(binding.recyclerView, R.string.load_data_failure, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}