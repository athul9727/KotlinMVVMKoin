package com.starapps.kotlinmvvm.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.starapps.kotlinmvvm.R
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartActivityTest : TestCase() {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<StartActivity>(
        StartActivity::class.java
    )

//    @Rule
//    @JvmField
//    var activityRule1 = ActivityTestRule<MainActivity>(
//        MainActivity::class.java
//    )

    @Test
    @Throws(Exception::class)
    fun check_startactivity() {
        onView(withId(R.id.bt_start))
            .check(matches(isDisplayed()))

        onView(withId(R.id.bt_start))
            .perform(click())

        onView(withId(R.id.ed_input)).perform(ViewActions.typeText("list"))

        onView(withId(R.id.bt_search))
            .perform(click())

        closeSoftKeyboard()

        Thread.sleep(5000)
    }

//    fun testOnCreate() {}
//
//    fun testOnItemClick() {}
//
//    fun testOnStartClick() {}
}