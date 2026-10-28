package by.arsy.jetpackbydmitryosin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import org.junit.Rule
import org.junit.Test

class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginFlow_correctInput_showsGreeting() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule
            .onNodeWithTag(testTag = "input_field")
            .performTextInput(text = "user")


        composeTestRule
            .onNodeWithText(text = "Enter")
            .assertIsEnabled()
            .performClick()

        Espresso.closeSoftKeyboard()

        composeTestRule
            .onNodeWithText("Hello, user")
            .assertIsDisplayed()

    }

}