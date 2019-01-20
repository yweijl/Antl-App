package com.avansprojects.antl;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.avansprojects.antl.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FriendOverviewFragment {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void friendOverviewFragment() {
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
withClassName(is("android.widget.LinearLayout")),
0)),
4)));
        appCompatButton.perform(scrollTo(), click());
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(700);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
        
        ViewInteraction bottomNavigationItemView = onView(
allOf(withId(R.id.destination_friends), withContentDescription("friends"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
1),
isDisplayed()));
        bottomNavigationItemView.perform(click());
        
        ViewInteraction imageButton = onView(
allOf(withId(R.id.addFriendButton),
childAtPosition(
allOf(withId(R.id.eventFragment),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
1),
isDisplayed()));
        imageButton.check(matches(isDisplayed()));
        
        ViewInteraction floatingActionButton = onView(
allOf(withId(R.id.addFriendButton),
childAtPosition(
allOf(withId(R.id.eventFragment),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
1),
isDisplayed()));
        floatingActionButton.perform(click());
        
        ViewInteraction textView = onView(
allOf(withId(R.id.friendCode), withText("2177-5501-3773"),
childAtPosition(
childAtPosition(
withId(R.id.constraintLayout),
0),
3),
isDisplayed()));
        textView.check(matches(withText("2177-5501-3773")));
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.editFriendCode), withText("FriendCode"),
childAtPosition(
childAtPosition(
withId(R.id.constraintLayout),
1),
1),
isDisplayed()));
        appCompatEditText.perform(replaceText("0000-0000-0000"));
        
        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.editFriendCode), withText("0000-0000-0000"),
childAtPosition(
childAtPosition(
withId(R.id.constraintLayout),
1),
1),
isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatButton2 = onView(
allOf(withId(R.id.next_button), withText("next"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
0),
isDisplayed()));
        appCompatButton2.perform(click());
        
        ViewInteraction bottomNavigationItemView2 = onView(
allOf(withId(R.id.destination_events), withContentDescription("events"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
0),
isDisplayed()));
        bottomNavigationItemView2.perform(click());
        
        ViewInteraction bottomNavigationItemView3 = onView(
allOf(withId(R.id.destination_events), withContentDescription("events"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
0),
isDisplayed()));
        bottomNavigationItemView3.perform(click());
        
        ViewInteraction bottomNavigationItemView4 = onView(
allOf(withId(R.id.destination_friends), withContentDescription("friends"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
1),
isDisplayed()));
        bottomNavigationItemView4.perform(click());
        
        ViewInteraction textView2 = onView(
allOf(withId(R.id.friendName), withText("user1"),
childAtPosition(
allOf(withId(R.id.friendCardConstraintLayout),
childAtPosition(
withId(R.id.cardView),
0)),
0),
isDisplayed()));
        textView2.check(matches(withText("user1")));
        
        ViewInteraction floatingActionButton2 = onView(
allOf(withId(R.id.addFriendButton),
childAtPosition(
allOf(withId(R.id.eventFragment),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
1),
isDisplayed()));
        floatingActionButton2.perform(click());
        
        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.editFriendCode), withText("FriendCode"),
childAtPosition(
childAtPosition(
withId(R.id.constraintLayout),
1),
1),
isDisplayed()));
        appCompatEditText3.perform(replaceText("9999-9999-9999"));
        
        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.editFriendCode), withText("9999-9999-9999"),
childAtPosition(
childAtPosition(
withId(R.id.constraintLayout),
1),
1),
isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatButton3 = onView(
allOf(withId(R.id.next_button), withText("next"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
0),
isDisplayed()));
        appCompatButton3.perform(click());
        
        ViewInteraction bottomNavigationItemView5 = onView(
allOf(withId(R.id.destination_friends), withContentDescription("friends"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
1),
isDisplayed()));
        bottomNavigationItemView5.perform(click());
        
        ViewInteraction textView3 = onView(
allOf(withId(R.id.friendName), withText("user2"),
childAtPosition(
allOf(withId(R.id.friendCardConstraintLayout),
childAtPosition(
withId(R.id.cardView),
0)),
0),
isDisplayed()));
        textView3.check(matches(withText("user2")));
        
        ViewInteraction textView4 = onView(
allOf(withId(R.id.friendName), withText("user1"),
childAtPosition(
allOf(withId(R.id.friendCardConstraintLayout),
childAtPosition(
withId(R.id.cardView),
0)),
0),
isDisplayed()));
        textView4.check(matches(withText("user1")));
        
        ViewInteraction materialButton = onView(
allOf(withId(R.id.removeFriendButton),
childAtPosition(
allOf(withId(R.id.friendCardConstraintLayout),
childAtPosition(
withId(R.id.cardView),
0)),
0),
isDisplayed()));
        materialButton.perform(click());
        
        ViewInteraction materialButton2 = onView(
allOf(withId(android.R.id.button1), withText("OK"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
3)));
        materialButton2.perform(scrollTo(), click());
        
        ViewInteraction textView5 = onView(
allOf(withId(R.id.friendName), withText("user2"),
childAtPosition(
allOf(withId(R.id.friendCardConstraintLayout),
childAtPosition(
withId(R.id.cardView),
0)),
0),
isDisplayed()));
        textView5.check(matches(withText("user2")));
        
        ViewInteraction bottomNavigationItemView6 = onView(
allOf(withId(R.id.destination_profile), withContentDescription("profile"),
childAtPosition(
childAtPosition(
withId(R.id.bottom_nav),
0),
2),
isDisplayed()));
        bottomNavigationItemView6.perform(click());
        
        ViewInteraction appCompatButton4 = onView(
allOf(withId(R.id.logoutButton), withText("Logout"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withId(R.id.nav_host_fragment),
0)),
1),
isDisplayed()));
        appCompatButton4.perform(click());
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
