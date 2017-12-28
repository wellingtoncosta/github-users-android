package br.com.wellingtoncosta.githubusers.ui.searchuser;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Collections;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.ActivitySearchUsersBinding;
import br.com.wellingtoncosta.githubusers.ui.base.BaseActivity;
import br.com.wellingtoncosta.githubusers.ui.userdetails.UserDetailsActivity;
import br.com.wellingtoncosta.githubusers.util.Messages;

import static android.databinding.DynamicUtil.safeUnbox;

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
        setupRecyclerView();
        setupSearchView();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        int orientation = layoutManager.getOrientation();

        binding.includeContent.recyclerView.setHasFixedSize(true);
        binding.includeContent.recyclerView.setLayoutManager(layoutManager);
        binding.includeContent.recyclerView.addItemDecoration(new DividerItemDecoration(this, orientation));
        binding.includeContent.recyclerView.setAdapter(new SearchUsersAdapter(onItemClickListener));
        binding.includeContent.swipeContainer.setOnRefreshListener(viewModel::loadUsers);
    }

    private void setupSearchView() {
        setSupportActionBar(binding.includeToolbar.toolbar);

        binding.includeToolbar.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() { }

            @Override
            public void onSearchViewClosed() {
                if(((SearchUsersAdapter)binding.includeContent.recyclerView.getAdapter()).getList().size() <= 1) {
                    viewModel.loadUsers();
                }
            }
        });

        binding.includeToolbar.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.loadUser(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (binding.includeToolbar.searchView.isSearchOpen()) {
            binding.includeToolbar.searchView.closeSearch();
        }

        viewModel.loadUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        binding.includeToolbar.searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.includeToolbar.searchView.isSearchOpen()) {
            binding.includeToolbar.searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(
                this,
                isLoading  -> binding.includeContent.swipeContainer.setRefreshing(safeUnbox(isLoading))
        );
    }

    public void observeResponse() {
        viewModel.getResponse().observe(this, response -> {
            if(response != null && response.status == Status.SUCCESS) {
                binding.includeContent.setUsers(response.data);
                binding.executePendingBindings();
            } else if (response != null && response.status == Status.ERROR) {
                binding.includeContent.setUsers(Collections.emptyList());
                showLongSnackbar(binding.getRoot(), Messages.getErrorMessage(response.throwable));
            }
        });
    }

}