package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class FlatTaxTests {

    lateinit var utah: State

    @Before
    fun beforeTest() {
        utah = State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(TaxBracket(-1, -1, "0.0495"))
        )
    }

    @Test
    fun flatTaxMethod_noIncome_flatTaxed() {
        val actualIncome = "0"

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(BigDecimal(actualIncome) * BigDecimal("0.0495"), BigDecimal(tax.total))
    }

    @Test
    fun flatTaxMethod_lowIncome_flatTaxed() {
        val actualIncome = "500"

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(BigDecimal(actualIncome) * BigDecimal("0.0495"), BigDecimal(tax.total))

    }

    @Test
    fun flatTaxMethod_medIncome_flatTaxed() {
        val actualIncome = "123456"

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(BigDecimal(actualIncome) * BigDecimal("0.0495"), BigDecimal(tax.total))

    }

    @Test
    fun flatTaxMethod_highIncome_flatTaxed() {
        val actualIncome = "1234567"

        val tax = calculateTax(actualIncome, utah)

        Assert.assertEquals(BigDecimal(actualIncome) * BigDecimal("0.0495"), BigDecimal(tax.total))

    }
}