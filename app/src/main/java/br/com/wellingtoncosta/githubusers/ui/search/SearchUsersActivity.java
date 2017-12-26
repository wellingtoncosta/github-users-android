package br.com.wellingtoncosta.githubusers.ui.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.databinding.ActivitySearchBinding;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class SearchUsersActivity extends DaggerAppCompatActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
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

}