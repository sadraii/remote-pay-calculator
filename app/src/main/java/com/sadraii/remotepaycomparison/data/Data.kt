package com.sadraii.remotepaycomparison.data

enum class StateTaxMethod {
    NONE,
    FLAT,
    PROGRESSIVE
}

data class State(
    val state: String,
    val taxMethod: StateTaxMethod,
    val taxBrackets: List<TaxBracket>? = null
)

fun calculateTax(income: Int, forState: State): CalculatedTax {
    return when (forState.taxMethod) {
        StateTaxMethod.NONE -> CalculatedTax(income.toFloat())
        StateTaxMethod.FLAT -> {
            val total =
                income * requireNotNull(forState.taxBrackets?.first()?.rate) { "Flat tax rate not found for ${forState.state}" }
            CalculatedTax(total = total)
        }
        StateTaxMethod.PROGRESSIVE -> {
            var remainingIncome = income
            var total = 0f
            var bracket: Int
            val perBracket: MutableList<Float> = mutableListOf()

            forState.taxBrackets?.forEach loop@{ taxBracket ->
                if (remainingIncome < 0) return@loop

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

data class CalculatedTax(
    val total: Float,
    val perBracket: List<Float>? = null
)

data class TaxBracket(val from: Int, val to: Int, val rate: Float)

val test = listOf(
    State(
        "Florida",
        StateTaxMethod.NONE
    ),
    State(
        "Utah",
        StateTaxMethod.FLAT,
        listOf(
            TaxBracket(-1, -1, 0.0495f)
        )
    ),
    State(
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
)