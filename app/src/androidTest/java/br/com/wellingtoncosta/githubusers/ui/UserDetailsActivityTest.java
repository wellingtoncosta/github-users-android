package br.com.wellingtoncosta.githubusers.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.wellingtoncosta.githubusers.R;
import br.com.wellingtoncosta.githubusers.ui.userdetails.UserDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.wellingtoncosta.githubusers.util.RecyclerViewItemCountAssertion.hasItemCount;
import static org.hamcrest.core.AllOf.allOf;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class UserDetailsActivityTest {

    private static final String USERNAME_TEST = "WellingtonCosta";

    @Rule
    public ActivityTestRule<UserDetailsActivity> activityRule =
            new ActivityTestRule<UserDetailsActivity>(UserDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    Intent result = new Intent(targetContext, UserDetailsActivity.class);
                    result.putExtra("username", USERNAME_TEST);
                    return result;
                }
            };

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void showActivityWithSuccess() {
        onView(allOf(
                withId(R.id.name),
                isDescendantOfA(withId(R.id.user_details))
                )).check(matches(isDisplayed()));

        onView(allOf(
                withId(R.id.username),
                isDescendantOfA(withId(R.id.user_details))
        )).check(matches(isDisplayed()));
    }

    @Test
    public void listReposWithSuccess() {
        onView(allOf(
                withId(R.id.recycler_view),
                isDescendantOfA(withId(R.id.list_repos))
        )).check(hasItemCount(5));
    }

    @Test
    public void listStarredReposWithSuccess() {
        onView(allOf(
                withText(R.string.repositories),
                isDescendantOfA(withId(R.id.tabs))
        )).perform(click());

        onView(allOf(
                withId(R.id.recycler_view),
                isDescendantOfA(withId(R.id.list_starred_repos))
        )).check(hasItemCount(5));
    }

}