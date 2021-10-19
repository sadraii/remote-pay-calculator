package com.sadraii.remotepaycomparison.data

import org.junit.Assert
import org.junit.Test

class NoTaxTests {

    @Test
    fun noTaxMethod_noIncome_notTaxed() {
        val actualIncome = 0
        val florida = State("Florida", StateTaxMethod.NONE)

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals(0f, tax.total)
    }

    @Test
    fun noTaxMethod_lowIncome_notTaxed() {
        val actualIncome = 500
        val florida = State("Florida", StateTaxMethod.NONE)

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals(500f, tax.total)
    }

    @Test
    fun noTaxMethod_medIncome_notTaxed() {
        val actualIncome = 123_456
        val florida = State("Florida", StateTaxMethod.NONE)

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals(123_456f, tax.total)
    }

    @Test
    fun noTaxMethod_highIncome_notTaxed() {
        val actualIncome = 1_234_567
        val florida = State("Florida", StateTaxMethod.NONE)

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals(1_234_567f, tax.total)
    }
}