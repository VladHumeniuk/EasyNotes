package masters.vlad.humeniuk.notesviper.uitest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import android.widget.EditText
import masters.vlad.humeniuk.notesviper.R
import masters.vlad.humeniuk.notesviper.presentation.main.MainActivity
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateCategoryTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testCreate() {
        onView(withId(R.id.action_categories)).perform(click())
        createCategory()
        onView(withText("new test category")).check(matches(isDisplayed()))
        deleteCategory()
    }

    @Test
    fun testEmptyDialog() {
        onView(withId(R.id.action_categories)).perform(click())
        onView(withId(R.id.add_category)).perform(click())
        onView(withId(R.id.action_save)).perform(click())
        onView(withText(R.string.empty_category_fields_error)).check(matches(isDisplayed()))
    }

    private fun createCategory() {
        onView(withId(R.id.add_category)).perform(click())
        onView(withId(R.id.title_view)).perform(typeText("new test category"))
        onView(withId(R.id.color_container)).perform(click())
        onView(instanceOf(Button::class.java)).perform(click())
        onView(withId(R.id.action_save)).perform(click())
    }

    private fun deleteCategory() {
        onView(withText("new test category")).perform(longClick())
        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_label)).perform(click())
    }
}