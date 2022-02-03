package com.jw.marvelcomics.ui.comic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jw.marvelcomics.R
import com.jw.marvelcomics.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComicFragmentTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenDisplayed_comicInfoIsDisplayed() {
        launchFragmentInHiltContainer<ComicFragment>()

        onView(allOf(withId(R.id.comic_title), isDisplayed()))
        onView(allOf(withId(R.id.comic_image), isDisplayed()))
        onView(allOf(withId(R.id.comic_description), isDisplayed()))
    }
}