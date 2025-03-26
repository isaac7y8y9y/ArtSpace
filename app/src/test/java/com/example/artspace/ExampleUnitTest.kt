package com.example.artspace

import androidx.compose.runtime.mutableIntStateOf
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testNextBtn() {
        val state = mutableIntStateOf(0)
        nextBtn(state)
        nextBtn(state)
        nextBtn(state)
        nextBtn(state)
        nextBtn(state)
        assertEquals(5, state.intValue)
        nextBtn(state)
        assertEquals(0, state.intValue)
    }
}