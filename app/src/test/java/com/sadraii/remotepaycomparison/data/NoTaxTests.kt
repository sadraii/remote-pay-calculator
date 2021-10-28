package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoTaxTests {

    lateinit var florida: State

    @Before
    fun beforeTest() {
        florida = State("Florida", StateTaxMethod.NONE)
    }

    @Test
    fun noTaxMethod_noIncome_notTaxed() {
        val actualIncome = "0"

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals("0.0", tax.total)
    }

    @Test
    fun noTaxMethod_lowIncome_notTaxed() {
        val actualIncome = "500"

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals("0.0", tax.total)
    }

    @Test
    fun noTaxMethod_medIncome_notTaxed() {
        val actualIncome = "123456"

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals("0.0", tax.total)
    }

    @Test
    fun noTaxMethod_highIncome_notTaxed() {
        val actualIncome = "1234567"

        val tax = calculateTax(actualIncome, florida)

        Assert.assertEquals("0.0", tax.total)
    }
}