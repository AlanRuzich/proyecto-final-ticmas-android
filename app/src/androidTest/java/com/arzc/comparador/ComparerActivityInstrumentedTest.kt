package com.arzc.comparador

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule

@RunWith(AndroidJUnit4::class)
class ComparerActivityInstrumentedTest {
    @get: Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(ComparerActivity::class.java)

    @Before fun before() {}
    @After  fun after()  {}

    @Test fun comparerActivity_CompareInitialStrings() {
        Espresso.onView(ViewMatchers.withId(R.id.compareButton)).perform(
            ViewActions.click(),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(
            ViewAssertions.matches(ViewMatchers.withText(R.string.textEqual))
        )
    }

    @Test fun comparerActivity_CompareEqualStrings() {
        Espresso.onView(ViewMatchers.withId(R.id.editText1)).perform(
            ViewActions.typeText("foo"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(ViewMatchers.withId(R.id.editText2)).perform(
            ViewActions.typeText("foo"),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.compareButton)).perform(
            ViewActions.click(),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(
            ViewAssertions.matches(ViewMatchers.withText(R.string.textEqual))
        )
    }

    @Test fun comparerActivity_CompareNonEqualStrings() {
        Espresso.onView(ViewMatchers.withId(R.id.editText1)).perform(
            ViewActions.typeText("bar"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(ViewMatchers.withId(R.id.editText2)).perform(
            ViewActions.typeText("Bar"),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.compareButton)).perform(
            ViewActions.click(),
            ViewActions.closeSoftKeyboard()
        )

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(
            ViewAssertions.matches(ViewMatchers.withText(R.string.textNotEqual))
        )
    }
}