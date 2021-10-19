package com.sadraii.remotepaycomparison.data

import org.junit.Assert
import org.junit.Test

class FlatTaxTests {

    @Test
    fun flatTaxMethod_noIncome_flatTaxed() {
        val actualIncome = 0
        val utah = State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495f)
            )
        )

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(0 * 0.0495f, tax.total)
    }

    @Test
    fun flatTaxMethod_lowIncome_flatTaxed() {
        val actualIncome = 500
        val utah = State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495f)
            )
        )

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(500 * 0.0495f, tax.total)
    }

    @Test
    fun flatTaxMethod_medIncome_flatTaxed() {
        val actualIncome = 123_456
        val utah = State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495f)
            )
        )

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(123_456 * 0.0495f, tax.total)
    }

    @Test
    fun flatTaxMethod_highIncome_flatTaxed() {
        val actualIncome = 1_234_567
        val utah = State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495f)
            )
        )

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(1_234_567 * 0.0495f, tax.total)
    }
}