package br.com.wellingtoncosta.githubusers.ui.details.repository;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wellingtoncosta.githubusers.R;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class ListRepositoriesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_repositories, container, false);
    }

}