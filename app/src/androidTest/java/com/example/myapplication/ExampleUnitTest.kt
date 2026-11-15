package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.junit.Test

import org.junit.Before
import org.junit.Rule

class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyApplicationTheme() {
                WaterTracker()
            }
        }
    }

    private fun addWater(times: Int) {
        repeat(times) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
    }

    @Test
    fun addButton_existsAndIncreasesWater() {
        composeTestRule.onNodeWithText("+250 мл")
            .assertExists()
            .assertHasClickAction()
        composeTestRule.onNodeWithText("0 мл").assertExists()

        addWater(2)

        composeTestRule.onNodeWithText("500 мл").assertExists()
    }

    @Test
    fun finishDayButton_existsAndResetsWater() {
        composeTestRule.onNodeWithText("Завершить день")
            .assertExists()
            .assertHasClickAction()

        addWater(2)
        composeTestRule.onNodeWithText("500 мл").assertExists()

        composeTestRule.onNodeWithText("Завершить день").performClick()

        composeTestRule.onNodeWithText("0 мл").assertExists()
    }

    @Test
    fun streakText_showsDaysWithEnoughWater() {
        composeTestRule
            .onNodeWithText("Дней подряд с 1500 мл и больше: 0")
            .assertExists()

        addWater(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule
            .onNodeWithText("Дней подряд с 1500 мл и больше: 1")
            .assertExists()

        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule
            .onNodeWithText("Дней подряд с 1500 мл и больше: 0")
            .assertExists()
    }
}
