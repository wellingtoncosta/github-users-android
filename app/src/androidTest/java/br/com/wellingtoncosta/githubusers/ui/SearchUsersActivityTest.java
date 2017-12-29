package br.com.wellingtoncosta.githubusers.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.ui.searchuser.SearchUsersActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.wellingtoncosta.githubusers.util.RecyclerViewItemCountAssertion.hasItemCount;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SearchUsersActivityTest {

    @Rule
    public ActivityTestRule<SearchUsersActivity> activityRule = new ActivityTestRule<>(SearchUsersActivity.class, true, true);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Test
    public void showActivityWithSuccess() {
        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void searchUserByUsernameWithSuccess() {
        onView(withId(R.id.action_search))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.searchTextView))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText(USERNAME_TEST))
                .check(matches(withText(USERNAME_TEST)))
                .perform(pressImeActionButton());

        onView(withId(R.id.recycler_view))
                .check(hasItemCount(1));
    }

}