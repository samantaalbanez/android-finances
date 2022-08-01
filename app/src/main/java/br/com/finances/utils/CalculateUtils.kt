package br.com.finances.utils

import br.com.finances.utils.FormatUtils.format

private const val PERCENTAGE_INVESTMENTS = 30
private const val PERCENTAGE_EXPENSES = 70
private const val PERCENTAGE_HUNDRED = 100

object CalculateUtils {

    fun Double.calculateInvestments(): String {
        this.toBigDecimal().let { value ->
            val calculate = value.multiply(
                PERCENTAGE_INVESTMENTS.toBigDecimal()
            ).divide(PERCENTAGE_HUNDRED.toBigDecimal())
            return value.minus(calculate).format()
        }
    }

    fun Double.calculateExpenses(): String {
        this.toBigDecimal().let { value ->
            val calculate = value.multiply(
                PERCENTAGE_EXPENSES.toBigDecimal()
            ).divide(PERCENTAGE_HUNDRED.toBigDecimal())
            return value.minus(calculate).format()
        }
    }

}