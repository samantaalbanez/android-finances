package br.com.finances.utils

import br.com.finances.utils.FormatUtils.format

object TextUtils {

    fun textSelic(textResources: String, value: String) = "$textResources $value%"

    fun textToolbar(textResources: String, value: String) = "$textResources $value!"

    fun textSalary(textResources: String, value: Double) = "$textResources ${value.format()}"

    fun textSuggestion(textResources: String, value: String) = "$textResources $value"

}