package br.com.wellingtoncosta.githubusers.ui.details.starreds;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.FragmentListStarredReposBinding;
import br.com.wellingtoncosta.githubusers.util.Messages;
import dagger.android.support.DaggerFragment;

import static android.databinding.DynamicUtil.safeUnbox;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class ListStarredReposFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ListStarredReposViewModel viewModel;

    private FragmentListStarredReposBinding binding;

    public static ListStarredReposFragment newInstance(String username) {
        ListStarredReposFragment fragment = new ListStarredReposFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListStarredReposViewModel.class);

        observeLoadingStatus();
        observeResponse();

        if (getArguments() != null) {
            viewModel.loadStarredRepos(getArguments().getString("username"));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_starred_repos, container, false);

        setupRecyclerView();

        if (getArguments() != null) {
            String username = getArguments().getString("username");
            binding.swipeContainer.setOnRefreshListener(() -> viewModel.loadStarredRepos(username));
        }

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ListStarredReposAdapter());

        if (getActivity() != null) {
            binding.recyclerView.addItemDecoration(new DividerItemDecoration(
                    getActivity(),
                    layoutManager.getOrientation()
            ));
        }
    }

    public void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(
                this,
                isLoading  -> binding.swipeContainer.setRefreshing(safeUnbox(isLoading))
        );
    }

    public void observeResponse() {
        viewModel.getResponse().observe(this, response -> {
            if(response != null && response.status == Status.SUCCESS) {
                binding.setRepos(response.data);
                binding.executePendingBindings();
            } else if (response != null && response.status == Status.ERROR) {
                Snackbar.make(binding.getRoot(), Messages.getErrorMessage(response.throwable), Snackbar.LENGTH_LONG).show();
            }
        });
    }

}