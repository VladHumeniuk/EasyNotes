package masters.vlad.humeniuk.notesviper.uitest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import masters.vlad.humeniuk.notesviper.R
import masters.vlad.humeniuk.notesviper.presentation.main.MainActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditCategoryTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testEdit() {
        onView(withId(R.id.action_categories)).perform(click())
        createCategory()

        onView(withText("new test category")).perform(longClick())
        onView(withId(R.id.title_view)).perform(clearText())
        onView(withId(R.id.title_view)).perform(typeText("edited test category"))
        onView(withId(R.id.color_container)).perform(click())
        onView(CoreMatchers.instanceOf(Button::class.java)).perform(click())
        onView(withId(R.id.action_save)).perform(click())

        onView(withText("edited test category")).check(matches(isDisplayed()))
        deleteCategory()
    }

    @Test
    fun testEmptyDialog() {
        onView(withId(R.id.action_categories)).perform(click())
        createCategory()
        onView(withText("new test category")).perform(longClick())
        onView(withId(R.id.title_view)).perform(clearText())
        onView(withId(R.id.action_save)).perform(click())
        onView(withText(R.string.empty_category_fields_error)).check(matches(isDisplayed()))
        onView(withText(R.string.ok_label)).perform(click())

        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_label)).perform(click())
    }

    @Test
    fun testDelete() {
        onView(withId(R.id.action_categories)).perform(click())
        createCategory()
        onView(withText("new test category")).perform(longClick())
        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_category_confirmation_text)).check(matches(isDisplayed()))
        onView(withText(R.string.delete_label)).perform(click())
    }

    private fun createCategory() {
        onView(withId(R.id.add_category)).perform(click())
        onView(withId(R.id.title_view)).perform(typeText("new test category"))
        onView(withId(R.id.color_container)).perform(click())
        onView(CoreMatchers.instanceOf(Button::class.java)).perform(click())
        onView(withId(R.id.action_save)).perform(click())
    }

    private fun deleteCategory() {
        onView(withText("edited test category")).perform(longClick())
        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_label)).perform(click())
    }
}