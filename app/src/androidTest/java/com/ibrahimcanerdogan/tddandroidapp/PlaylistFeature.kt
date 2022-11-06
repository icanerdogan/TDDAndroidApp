package com.ibrahimcanerdogan.tddandroidapp

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    // ActivityTestRule deprecated.
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get


    @Test
    fun displayScreenTitle() {
        assertDisplayed("Posts")
    }

    @Test
    fun displaysListOfPosts() {
        Thread.sleep(4000)

        assertRecyclerViewItemCount(R.id.posts_list, 10)

        onView(allOf(withId(R.id.post_title), isDescendantOfA(nthChildOf(withId(R.id.posts_list), 0))))
            .check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.post_body), isDescendantOfA(nthChildOf(withId(R.id.posts_list), 0))))
            .check(matches(withText("quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas totam nnostrum rerum est autem sunt rem eveniet architecto")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.post_image), isDescendantOfA(nthChildOf(withId(R.id.posts_list), 0))))
            .check(matches(withDrawable(R.drawable.post_image)))
            .check(matches(isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.let {
                    description.appendText("position $childPosition of parent")
                    parentMatcher.describeTo(description)
                }
            }

            override fun matchesSafely(item: View?): Boolean {
                item?.let {
                    if (item.parent !is ViewGroup) return false
                    val parent = item.parent as ViewGroup

                    return (parentMatcher.matches(parent)
                            && parent.childCount > childPosition
                            && parent.getChildAt(childPosition) == item)
                }
                return false
            }
        }
    }
}