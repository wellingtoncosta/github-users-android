package br.com.wellingtoncosta.githubusers.ui.details.repos;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.data.remote.response.Status;
import br.com.wellingtoncosta.githubusers.databinding.FragmentListRepositoriesBinding;
import dagger.android.support.DaggerFragment;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class ListReposFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ListReposViewModel viewModel;

    private FragmentListRepositoriesBinding binding;

    public static ListReposFragment newInstance(String username) {
        ListReposFragment fragment = new ListReposFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListReposViewModel.class);

        observeLoadingStatus();
        observeResponse();

        if (getArguments() != null) {
            viewModel.loadRepos(getArguments().getString("username"));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_repositories, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(new ListReposAdapter());

        if (getArguments() != null) {
            String username = getArguments().getString("username");
            binding.swipeContainer.setOnRefreshListener(() -> viewModel.loadRepos(username));
        }

        return binding.getRoot();
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
                binding.setRepos(response.data);
                binding.executePendingBindings();
            }
        });
    }

}