package br.com.finances.utils

import br.com.finances.utils.CalculateUtils.calculateExpenses
import br.com.finances.utils.CalculateUtils.calculateInvestments
import org.junit.Assert

import org.junit.Test

class CalculateUtilsTest {

    @Test
    fun when_pass_salary_per_parameter_should_calculate_value_investments() {
        val salary = 1000.0
        val calculate = salary.calculateInvestments()
        Assert.assertEquals(calculate, "300,00")
    }

    @Test
    fun when_pass_salary_per_parameter_should_calculate_value_exppenses() {
        val salary = 1000.0
        val calculate = salary.calculateExpenses()
        Assert.assertEquals(calculate, "700,00")
    }

}