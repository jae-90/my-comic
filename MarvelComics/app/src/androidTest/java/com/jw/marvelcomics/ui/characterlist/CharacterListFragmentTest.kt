package com.jw.marvelcomics.ui.characterlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jw.marvelcomics.R
import com.jw.marvelcomics.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CharacterListFragmentTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenDisplayed_characterListFromRepoIsDisplayed() {
        launchFragmentInHiltContainer<CharacterListFragment>()
        scrollAtAndCheckTestVisible(0, "name1")
        scrollAtAndCheckTestVisible(1, "name2")
        scrollAtAndCheckTestVisible(2, "name3")
    }

    private fun scrollAtAndCheckTestVisible(position: Int, text: String) {
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<CharacterListAdapter.CharacterViewHolder>(position))

        onView(withText(text)).check(matches(isDisplayed()))
    }
}