package com.example.artspace

import androidx.compose.runtime.mutableIntStateOf
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtSpaceTest {

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

        // correct vv
    }

    @Test
    fun testPrevBtn() {
        val state = mutableIntStateOf(5)
        prevBtn(state)
        prevBtn(state)
        prevBtn(state)
        prevBtn(state)
        prevBtn(state)
        assertEquals(0, state.intValue)
        prevBtn(state)
        assertEquals(5, state.intValue)

        // correct vv
    }
}