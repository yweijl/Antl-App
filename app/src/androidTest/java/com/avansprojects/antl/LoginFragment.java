package com.avansprojects.antl;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.avansprojects.antl.retrofit.OkHttpProvider;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;

import com.jakewharton.espresso.OkHttp3IdlingResource;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginFragment {
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = OkHttp3IdlingResource.create(
                "okhttp", OkHttpProvider.getOkHttpInstance());
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mIdlingResource);
    }

    @Test
    public void successfulLoginNavigatesToEventsOverview() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.LoginTextView), withText("LOGIN"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.username_text_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.username_text_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("tester"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.password_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password_text_input),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Test@123"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_button), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.eventFragment),
                        childAtPosition(
                                allOf(withId(R.id.nav_host_fragment),
                                        childAtPosition(
                                                withId(R.id.container),
                                                1)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.createEventFab),
                        childAtPosition(
                                allOf(withId(R.id.eventFragment),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        Log.i("Espresso: ", "Event page reached");

        ViewInteraction bottomNavigationItemView = onView(
                Matchers.allOf(withId(R.id.destination_profile), withContentDescription("profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                Matchers.allOf(withId(R.id.logoutButton), withText("Logout"),
                        childAtPosition(
                                Matchers.allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        textView = onView(
                Matchers.allOf(withId(R.id.LoginTextView), withText("LOGIN"),
                        childAtPosition(
                                Matchers.allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }

    @Test
    public void unsuccessfulLoginShowsToast() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.LoginTextView), withText("LOGIN"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.username_text_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.username_text_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("testor"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.password_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password_text_input),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Test@123"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_button), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.eventFragment),
                        childAtPosition(
                                allOf(withId(R.id.nav_host_fragment),
                                        childAtPosition(
                                                withId(R.id.container),
                                                1)),
                                0),
                        isDisplayed()));
        viewGroup.check(doesNotExist());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.createEventFab),
                        childAtPosition(
                                allOf(withId(R.id.eventFragment),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                1),
                        isDisplayed()));
        imageButton.check(doesNotExist());

        onView(
                withText("Login failed")).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
