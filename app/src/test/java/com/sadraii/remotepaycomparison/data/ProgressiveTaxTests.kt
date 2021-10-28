package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.DataUtils.calculateTax
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ProgressiveTaxTests {

    lateinit var california: State
    lateinit var simpleBracket: State

    @Before
    fun beforeTest() {
        california = State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(1, 8_932, "0.01"),
                TaxBracket(8_933, 21_175, "0.02"),
                TaxBracket(21_176, 33_421, "0.04"),
                TaxBracket(33_422, 46_394, "0.06"),
                TaxBracket(46_395, 58_634, "0.08"),
                TaxBracket(58_635, 299_508, "0.093"),
                TaxBracket(299_509, 359_407, "0.103"),
                TaxBracket(359_408, 599_012, "0.113"),
                TaxBracket(599_013, 1_000_000, "0.123"),
                TaxBracket(1_000_001, -1, "0.133")
            ),
            taxBracketsMarried = listOf(
                TaxBracket(1, 17_864, "0.01"),
                TaxBracket(17_865, 42_350, "0.02"),
                TaxBracket(42_351, 66_842, "0.04"),
                TaxBracket(66_843, 92_788, "0.06"),
                TaxBracket(92_789, 117_268, "0.08"),
                TaxBracket(117_269, 599_016, "0.093"),
                TaxBracket(599_017, 718_814, "0.103"),
                TaxBracket(718_815, 1_000_000, "0.113"),
                TaxBracket(1_000_001, 1_198_024, "0.123"),
                TaxBracket(1_198_025, -1, "0.133")
            )
        )
        simpleBracket = State(
            "s",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(1, 10, "0.01"),
                TaxBracket(11, 20, "0.02"),
                TaxBracket(21, -1, "0.03")
            )
        )
    }

    @Test
    fun simpleBracketTest() {
        val actualIncome = "25"

        val tax = calculateTax(actualIncome, simpleBracket)

        Assert.assertEquals(
            ((BigDecimal("10") - BigDecimal("1") + BigDecimal.ONE) * BigDecimal("0.01") +
                    (BigDecimal("20") - BigDecimal("11") + BigDecimal.ONE) * BigDecimal("0.02") +
                    (BigDecimal("25") - BigDecimal("21") + BigDecimal.ONE) * BigDecimal("0.03")).toString(),
            BigDecimal(tax.total).toString()
        )
    }

    @Test
    fun progressiveTaxMethod_noIncome_progressiveTaxed() {
        val actualIncome = "0"

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(BigDecimal(actualIncome).toString(), BigDecimal(tax.total).toString())
    }

    @Test
    fun progressiveTaxMethod_lowIncome_progressiveTaxed() {
        val actualIncome = "500"

        val tax = calculateTax(actualIncome, california)
            val x = (2..5).count()
        Assert.assertEquals(
            (BigDecimal("500") * BigDecimal("0.01")).toString(),
            BigDecimal(tax.total).toString()
        )
    }

    @Test
    fun progressiveTaxMethod_medIncome_progressiveTaxed() {
        val actualIncome = "123456"

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            ((BigDecimal("8932") - BigDecimal("1") + BigDecimal.ONE) * BigDecimal("0.01") +
                    (BigDecimal("21175") - BigDecimal("8933") + BigDecimal.ONE) * BigDecimal("0.02") +
                    (BigDecimal("33421") - BigDecimal("21176") + BigDecimal.ONE) * BigDecimal("0.04") +
                    (BigDecimal("46394") - BigDecimal("33422") + BigDecimal.ONE) * BigDecimal("0.06") +
                    (BigDecimal("58634") - BigDecimal("46395") + BigDecimal.ONE) * BigDecimal("0.08") +
                    (BigDecimal("123456") - BigDecimal("58635") + BigDecimal.ONE) * BigDecimal("0.093")).toString(),
            BigDecimal(tax.total).toString()
        )
    }

    @Test
    fun progressiveTaxMethod_highIncome_progressiveTaxed() {
        val actualIncome = "1234567"

        val tax = calculateTax(actualIncome, california)

        Assert.assertEquals(
            ((BigDecimal("8932") - BigDecimal("1") + BigDecimal.ONE) * BigDecimal("0.01") +
                    (BigDecimal("21175") - BigDecimal("8933") + BigDecimal.ONE) * BigDecimal("0.02") +
                    (BigDecimal("33421") - BigDecimal("21176") + BigDecimal.ONE) * BigDecimal("0.04") +
                    (BigDecimal("46394") - BigDecimal("33422") + BigDecimal.ONE) * BigDecimal("0.06") +
                    (BigDecimal("58634") - BigDecimal("46395") + BigDecimal.ONE) * BigDecimal("0.08") +
                    (BigDecimal("299508") - BigDecimal("58635") + BigDecimal.ONE) * BigDecimal("0.093") +
                    (BigDecimal("359407") - BigDecimal("299509") + BigDecimal.ONE) * BigDecimal("0.103") +
                    (BigDecimal("599012") - BigDecimal("359408") + BigDecimal.ONE) * BigDecimal("0.113") +
                    (BigDecimal("1000000") - BigDecimal("599013") + BigDecimal.ONE) * BigDecimal("0.123") +
                    (BigDecimal("1234567") - BigDecimal("1000001") + BigDecimal.ONE) * BigDecimal("0.133")).toString(),
            BigDecimal(tax.total).toString()
        )
    }


}