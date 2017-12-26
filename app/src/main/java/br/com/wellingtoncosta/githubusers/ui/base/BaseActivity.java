package br.com.wellingtoncosta.githubusers.ui.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public abstract class BaseActivity<T extends ViewModel> extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected T viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupViews();
    }

    public abstract void setupViews();

    public abstract void setupViewModel();

    public void setupToolbar(Toolbar toolbar, int stringTitleResId, boolean showBackButton) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(getString(stringTitleResId));

                if (showBackButton) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    public void showLongSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    public void showLongSnackbar(View view, int stringResId) {
        Snackbar.make(view, getString(stringResId), Snackbar.LENGTH_LONG).show();
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }

}