package com.sadraii.remotepaycomparison.data

import org.junit.Assert.assertEquals
import org.junit.Test

class DataTests {

    @Test
    fun getState_returnsState() {
        val expected = State(
            "Texas",
            StateTaxMethod.NONE
        )

        val actual = StateData.getState("Texas")

        assertEquals(expected, actual)
    }
}