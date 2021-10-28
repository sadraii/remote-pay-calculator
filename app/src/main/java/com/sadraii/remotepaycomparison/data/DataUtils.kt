package com.sadraii.remotepaycomparison.data

import com.sadraii.remotepaycomparison.data.StateTaxMethod.*
import java.math.BigDecimal

object DataUtils {
    fun calculateTax(income: String, forState: State): CalculatedTax {
        return when (forState.taxMethod) {
            NONE -> CalculatedTax()
            FLAT -> {
                val total =
                    BigDecimal(income) * BigDecimal(requireNotNull(forState.taxBracketsSingle?.first()?.rate) { "Flat tax rate not found for ${forState.name}" })
                CalculatedTax(total.toString())
            }
            PROGRESSIVE -> {
                var remainingIncome = BigDecimal(income)
                var bracketRange = BigDecimal("0")
                var total = BigDecimal("0")
                val bracketTotals: MutableList<BigDecimal> = mutableListOf()

                forState.taxBracketsSingle?.forEach loop@{ taxBracket ->
                    if (remainingIncome <= BigDecimal.ZERO) return@loop

                    // Need to add 1 to count numbers correctly (e.g. 10 - 1 = 9 + 1 = 10 numbers)
                    bracketRange = BigDecimal(taxBracket.to - taxBracket.from + 1)

                    val bracket = if (taxBracket.to != -1 && remainingIncome > bracketRange) {
                        bracketRange * BigDecimal(taxBracket.rate)
                    } else {
                        remainingIncome * BigDecimal(taxBracket.rate)
                    }
                    bracketTotals.add(bracket)

                    total += bracket
                    remainingIncome -= bracketRange
                }

                CalculatedTax(total.toString(), bracketTotals)
            }
        }
    }
}

data class CalculatedTax(
    val total: String = "0.0",
    val bracketTotals: List<BigDecimal>? = null
)