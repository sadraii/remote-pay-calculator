package com.sadraii.remotepaycomparison.data

object DataUtils {
    fun calculateTax(income: Int, forState: State): CalculatedTax {
        return when (forState.taxMethod) {
            StateTaxMethod.NONE -> CalculatedTax()
            StateTaxMethod.FLAT -> {
                val total =
                    income * requireNotNull(forState.taxBracketsSingle?.first()?.rate) { "Flat tax rate not found for ${forState.name}" }
                CalculatedTax(total = total)
            }
            StateTaxMethod.PROGRESSIVE -> {
                var remainingIncome = income
                var total = 0.0
                var bracket: Int
                val perBracket: MutableList<Double> = mutableListOf()

                forState.taxBracketsSingle?.forEach loop@{ taxBracket ->
                    if (remainingIncome <= 0) return@loop

                    bracket = taxBracket.to - taxBracket.from

                    val thisBracket = if (taxBracket.to != -1 && remainingIncome > bracket) {
                        bracket * taxBracket.rate
                    } else {
                        remainingIncome * taxBracket.rate
                    }
                    perBracket.add(thisBracket)

                    total += thisBracket
                    remainingIncome -= bracket
                }

                CalculatedTax(total, perBracket)
            }
        }
    }
}

data class CalculatedTax(
    val total: Double = 0.0,
    val perBracket: List<Double>? = null
)