package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FlatTaxTests {

    lateinit var utah: State

    @Before
    fun beforeTest() {
        utah = State("Florida", StateTaxMethod.NONE)
    }

    @Test
    fun flatTaxMethod_noIncome_flatTaxed() {
        val actualIncome = 0

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(0 * 0.0495, tax.total, 0.0)
    }

    @Test
    fun flatTaxMethod_lowIncome_flatTaxed() {
        val actualIncome = 500

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(500 * 0.0495, tax.total, 0.0)
    }

    @Test
    fun flatTaxMethod_medIncome_flatTaxed() {
        val actualIncome = 123_456

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(123_456 * 0.0495, tax.total, 0.0)
    }

    @Test
    fun flatTaxMethod_highIncome_flatTaxed() {
        val actualIncome = 1_234_567

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(1_234_567 * 0.0495, tax.total, 0.0)
    }
}