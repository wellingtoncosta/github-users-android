package br.com.wellingtoncosta.githubusers.util;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Wellington Costa on 28/12/2017.
 */
public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int expectedCount;

    private RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    public static RecyclerViewItemCountAssertion hasItemCount(int expectedCount) {
        return new RecyclerViewItemCountAssertion(expectedCount);
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        int actualItemCount = recyclerView.getAdapter().getItemCount();
        assertThat(actualItemCount, is(expectedCount));
    }

}
