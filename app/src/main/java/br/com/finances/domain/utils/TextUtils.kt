package br.com.finances.domain.utils

import br.com.finances.domain.utils.FormatUtils.format

object TextUtils {

    fun textSelic(textResources: String, value: String) = "$textResources $value%"

    fun textToolbar(textResources: String, value: String) = "$textResources $value!"

    fun textSalary(textResources: String, value: Double) = "$textResources ${value.format()}"

    fun textSuggestion(textResources: String, value: String) = "$textResources $value"

}