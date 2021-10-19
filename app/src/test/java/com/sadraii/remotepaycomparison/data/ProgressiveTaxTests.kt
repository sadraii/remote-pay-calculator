package com.sadraii.remotepaycomparison.data

import org.junit.Assert
import org.junit.Test
import kotlin.math.roundToInt

class ProgressiveTaxTests {

    @Test
    fun progressiveTaxMethod_noIncome_progressiveTaxed() {
        val actualIncome = 0
        val california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            listOf(
                TaxBracket(0, 8_932, 1f),
                TaxBracket(8_933, 21_175, 2f),
                TaxBracket(21_176, 33_421, 4f),
                TaxBracket(33_422, 46_394, 6f),
                TaxBracket(46_395, 58_634, 8f),
                TaxBracket(58_635, 299_508, 9.3f),
                TaxBracket(299_509, 359_407, 10.3f),
                TaxBracket(359_408, 599_012, 11.3f),
                TaxBracket(599_013, -1, 12.3f)
            )
        )

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals((0 * 1f).roundToInt(), tax.total.roundToInt())
    }

    @Test
    fun progressiveTaxMethod_lowIncome_progressiveTaxed() {
        val actualIncome = 500
        val california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            listOf(
                TaxBracket(0, 8_932, 0.01f),
                TaxBracket(8_933, 21_175, 0.02f),
                TaxBracket(21_176, 33_421, 0.04f),
                TaxBracket(33_422, 46_394, 0.06f),
                TaxBracket(46_395, 58_634, 0.08f),
                TaxBracket(58_635, 299_508, 0.093f),
                TaxBracket(299_509, 359_407, 0.0103f),
                TaxBracket(359_408, 599_012, 0.0113f),
                TaxBracket(599_013, -1, 0.0123f)
            )
        )

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals((500 * 0.01f).roundToInt(), tax.total.roundToInt())
    }

    @Test
    fun progressiveTaxMethod_medIncome_progressiveTaxed() {
        val actualIncome = 123_456
        val california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            listOf(
                TaxBracket(0, 8_932, 0.01f),
                TaxBracket(8_933, 21_175, 0.02f),
                TaxBracket(21_176, 33_421, 0.04f),
                TaxBracket(33_422, 46_394, 0.06f),
                TaxBracket(46_395, 58_634, 0.08f),
                TaxBracket(58_635, 299_508, 0.093f),
                TaxBracket(299_509, 359_407, 0.0103f),
                TaxBracket(359_408, 599_012, 0.0113f),
                TaxBracket(599_013, -1, 0.0123f)
            )
        )

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            ((8_932 - 0) * 0.01f +
                    (21_175 - 8_933) * 0.02f +
                    (33_421 - 21_176) * 0.04f +
                    (46_394 - 33_422) * 0.06f +
                    (58_634 - 46_395) * 0.08f +
                    (123_456 - 58_635) * 0.093f).roundToInt(),
            tax.total.roundToInt()
        )
    }

    @Test
    fun progressiveTaxMethod_highIncome_progressiveTaxed() {
        val actualIncome = 1_234_567
        val california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            listOf(
                TaxBracket(0, 8_932, 0.01f),
                TaxBracket(8_933, 21_175, 0.02f),
                TaxBracket(21_176, 33_421, 0.04f),
                TaxBracket(33_422, 46_394, 0.06f),
                TaxBracket(46_395, 58_634, 0.08f),
                TaxBracket(58_635, 299_508, 0.093f),
                TaxBracket(299_509, 359_407, 0.103f),
                TaxBracket(359_408, 599_012, 0.113f),
                TaxBracket(599_013, 1_000_000, 0.123f),
                TaxBracket(1_000_001, -1, 0.133f)
            )
        )

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            ((8_932 - 0) * 0.01f +
                    (21_175 - 8_933) * 0.02f +
                    (33_421 - 21_176) * 0.04f +
                    (46_394 - 33_422) * 0.06f +
                    (58_634 - 46_395) * 0.08f +
                    (299_508 - 58_635) * 0.093f +
                    (359_407 - 299_509) * 0.103f +
                    (599_012 - 359_408) * 0.113f +
                    (1_000_000 - 599_013) * 0.123f +
                    (1_234_567 - 1_000_001) * 0.133f).roundToInt(),
            tax.total.roundToInt()
        )
    }


}