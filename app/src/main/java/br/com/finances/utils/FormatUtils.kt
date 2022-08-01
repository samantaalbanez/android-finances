package br.com.finances.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object FormatUtils {

    private val format: DecimalFormat by lazy {
        DecimalFormat("###,###.00")
    }

    fun Double.format(): String {
        format.decimalFormatSymbols = configureFormat()
        return format.format(this)
    }

    fun BigDecimal.format(): String {
        format.decimalFormatSymbols = configureFormat()
        return format.format(this)
    }

    private fun configureFormat(): DecimalFormatSymbols {
        val decimalFormatSymbols = DecimalFormatSymbols()
        with (decimalFormatSymbols) {
            decimalSeparator = ','
            groupingSeparator = '.'
            return this
        }
    }

}