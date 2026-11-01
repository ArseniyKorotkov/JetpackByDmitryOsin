package by.arsy.jetpackbydmitryosin

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CounterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_increments_when_clicked() {
        val textTag = "counter_value"
        val startValue = 0
        val incrementTimes = 3

        composeTestRule.setContent {
            CounterScreen()
        }

        composeTestRule
            .onNodeWithTag(testTag = textTag)
            .assertTextEquals(startValue.toString())

        repeat(incrementTimes) {
            composeTestRule
                .onNodeWithText("Increment")
                .performClick()
        }

        composeTestRule
            .onNodeWithTag(testTag = textTag)
            .assertTextEquals(incrementTimes.toString())

    }

}