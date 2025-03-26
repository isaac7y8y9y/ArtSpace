package com.example.artspace

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.atomic.AtomicBoolean

@RunWith(AndroidJUnit4::class)
class ArtSpaceUITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Idling resource to wait for Compose UI to render
    private val uiIdle = object : IdlingResource {
        private val isIdle = AtomicBoolean(true)
        private var callback: IdlingResource.ResourceCallback? = null

        override fun getName(): String = "UIIdle"
        override fun isIdleNow(): Boolean = isIdle.get()
        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
            this.callback = callback
        }
        fun setIdle(idle: Boolean) {
            isIdle.set(idle)
            if (idle) callback?.onTransitionToIdle()
        }
    }

    @Test
    fun testInitialState() {
        IdlingRegistry.getInstance().register(uiIdle)
        uiIdle.setIdle(false)
        Thread.sleep(1000) // Wait for Compose to render
        uiIdle.setIdle(true)

        onView(withText(R.string.alice_n_rin_kimono))
            .check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(uiIdle)
    }

    @Test
    fun testNextButtonCyclesThroughImages() {
        IdlingRegistry.getInstance().register(uiIdle)
        uiIdle.setIdle(false)
        Thread.sleep(1000)
        uiIdle.setIdle(true)

        onView(withText(R.string.alice_n_rin_kimono))
            .check(matches(isDisplayed()))

        val titles = listOf(
            R.string.alice_n_rin,
            R.string.blue_knight,
            R.string.sword_woman,
            R.string.gm_commander,
            R.string.water_princess,
            R.string.alice_n_rin_kimono
        )

        titles.forEach { title ->
            onView(withText("Next")).perform(click())
            uiIdle.setIdle(false)
            Thread.sleep(200) // Wait for state change
            uiIdle.setIdle(true)
            onView(withText(title)).check(matches(isDisplayed()))
        }

        IdlingRegistry.getInstance().unregister(uiIdle)
    }

    @Test
    fun testPreviousButtonCyclesThroughImages() {
        IdlingRegistry.getInstance().register(uiIdle)
        uiIdle.setIdle(false)
        Thread.sleep(1000)
        uiIdle.setIdle(true)

        onView(withText(R.string.alice_n_rin_kimono))
            .check(matches(isDisplayed()))

        val titles = listOf(
            R.string.water_princess,
            R.string.gm_commander,
            R.string.sword_woman,
            R.string.blue_knight,
            R.string.alice_n_rin,
            R.string.alice_n_rin_kimono
        )

        titles.forEach { title ->
            onView(withText("Previous")).perform(click())
            uiIdle.setIdle(false)
            Thread.sleep(200)
            uiIdle.setIdle(true)
            onView(withText(title)).check(matches(isDisplayed()))
        }

        IdlingRegistry.getInstance().unregister(uiIdle)
    }

    @Test
    fun testButtonsVisibility() {
        IdlingRegistry.getInstance().register(uiIdle)
        uiIdle.setIdle(false)
        Thread.sleep(1000)
        uiIdle.setIdle(true)

        onView(withText("Previous")).check(matches(isDisplayed()))
        onView(withText("Next")).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(uiIdle)
    }

    @Test
    fun testTitleAndInfoSync() {
        IdlingRegistry.getInstance().register(uiIdle)
        uiIdle.setIdle(false)
        Thread.sleep(1000)
        uiIdle.setIdle(true)

        onView(withText(R.string.alice_n_rin_kimono)).check(matches(isDisplayed()))
        onView(withText(R.string.alice_n_rin_kimono_info)).check(matches(isDisplayed()))

        onView(withText("Next")).perform(click())
        uiIdle.setIdle(false)
        Thread.sleep(200)
        uiIdle.setIdle(true)

        onView(withText(R.string.alice_n_rin)).check(matches(isDisplayed()))
        onView(withText(R.string.alice_n_rin_info)).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(uiIdle)
    }
}