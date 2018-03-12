package masters.vlad.humeniuk.notesviper.uitest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import masters.vlad.humeniuk.notesviper.R
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil
import masters.vlad.humeniuk.notesviper.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateCategoryNoteTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testEmptyDialog() {
        onView(withId(R.id.action_categories)).perform(click())
        onView(withText(CategoryUtil.getDefaultCategory().name)).perform(click())
        onView(withId(R.id.add_note)).perform(click())
        onView(withId(R.id.action_save)).perform(click())
        onView(withText(R.string.empty_note_fields_error)).check(matches(isDisplayed()))
    }

    @Test
    fun test() {
//        for (i in 1..10) {
            var time = System.currentTimeMillis()
            createNote()
            time = System.currentTimeMillis() - time
            onView(withText("new test note")).check(matches(isDisplayed()))
            removeNote()
//            Log.i("Create category note test", "test " + i + " | time " + time)
//        }
    }

    private fun createNote() {
        onView(withId(R.id.action_categories)).perform(click())
        onView(withText(CategoryUtil.getDefaultCategory().name)).perform(click())
        onView(withId(R.id.add_note)).perform(click())
        onView(withId(R.id.title)).perform(typeText("new test note")).perform(closeSoftKeyboard())
        onView(withId(R.id.description)).perform(typeText("test note description"))
        onView(withId(R.id.action_save)).perform(click())
    }

    private fun removeNote() {
        onView(withText("new test note")).perform(click())
        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_label)).perform(click())
    }
}