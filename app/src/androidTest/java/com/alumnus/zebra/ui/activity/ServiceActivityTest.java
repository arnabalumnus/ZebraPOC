package com.alumnus.zebra.ui.activity;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alumnus.zebra.R;
import com.alumnus.zebra.utils.TestHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ServiceActivityTest {

    @Rule
    public ActivityScenarioRule<ServiceActivity> mActivityTestRule = new ActivityScenarioRule<>(ServiceActivity.class);


    @Test
    public void verifyLifeTimeServiceRunning() {
        TestHelper.INSTANCE.delay(300);
        onView(withId(R.id.high_freq)).perform(ViewActions.click());
        onView(withId(R.id.buttonLifeTimeService)).perform(ViewActions.click());
        TestHelper.INSTANCE.delay(3000);
        //TODO verify notification icons

    }
}