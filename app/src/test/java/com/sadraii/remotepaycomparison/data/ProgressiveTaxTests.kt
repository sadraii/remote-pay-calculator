package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgressiveTaxTests {

    lateinit var california: State

    @Before
    fun beforeTest() {
        california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            listOf(
                TaxBracket(0, 8_932, 0.01),
                TaxBracket(8_933, 21_175, 0.02),
                TaxBracket(21_176, 33_421, 0.04),
                TaxBracket(33_422, 46_394, 0.06),
                TaxBracket(46_395, 58_634, 0.08),
                TaxBracket(58_635, 299_508, 0.093),
                TaxBracket(299_509, 359_407, 0.103),
                TaxBracket(359_408, 599_012, 0.113),
                TaxBracket(599_013, 1_000_000, 0.123),
                TaxBracket(1_000_001, -1, 0.133)
            )
        )
    }

    @Test
    fun progressiveTaxMethod_noIncome_progressiveTaxed() {
        val actualIncome = 0

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(0 * 1.0, tax.total, 0.0)
    }

    @Test
    fun progressiveTaxMethod_lowIncome_progressiveTaxed() {
        val actualIncome = 500

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(500 * 0.01, tax.total, 0.0)
    }

    @Test
    fun progressiveTaxMethod_medIncome_progressiveTaxed() {
        val actualIncome = 123_456

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            (8_932 - 0) * 0.01 +
                    (21_175 - 8_933) * 0.02 +
                    (33_421 - 21_176) * 0.04 +
                    (46_394 - 33_422) * 0.06 +
                    (58_634 - 46_395) * 0.08 +
                    (123_456 - 58_635) * 0.093,
            tax.total,
            1.0
        )
    }

    @Test
    fun progressiveTaxMethod_highIncome_progressiveTaxed() {
        val actualIncome = 1_234_567

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            ((8_932 - 0) * 0.01 +
                    (21_175 - 8_933) * 0.02 +
                    (33_421 - 21_176) * 0.04 +
                    (46_394 - 33_422) * 0.06 +
                    (58_634 - 46_395) * 0.08 +
                    (299_508 - 58_635) * 0.093 +
                    (359_407 - 299_509) * 0.103 +
                    (599_012 - 359_408) * 0.113 +
                    (1_000_000 - 599_013) * 0.123 +
                    (1_234_567 - 1_000_001) * 0.133),
            tax.total,
            2.0
        )
    }


}