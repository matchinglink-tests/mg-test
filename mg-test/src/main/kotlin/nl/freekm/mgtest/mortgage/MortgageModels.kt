package nl.freekm.mgtest.mortgage

import java.time.LocalDate
import kotlin.math.pow

class Mortgage(
    val clientCode: String,
    val portfolioCode: String,
    val identifier: String,
    val originalNotional: Double,
    val type: MortgageType,
    val currentNotional: Double,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val interestRate: Double,
    val fixedUntil: LocalDate,
    val currentMonthlyInterest: Double,
    val currentMonthlyAnnuity: Double,
    val currentLinearRepayment: Double,
    val currentPolicyPremium: Double,
    val propertyValue: Double,
    val propertyValueDate: LocalDate?,
    val properLiquidationValue: Double,
    val policyAndOtherValue: Double,
    val postalCode: String?,
    val isNhg: Boolean
) {

    fun toSummary(): MortgageSummary {
        return MortgageSummary(
            this.clientCode,
            this.portfolioCode,
            this.identifier,
            this.type
        )
    }

    fun monthlyInterestRate(): Double {
        return ((1 + this.interestRate).pow((1.toDouble()) / 12) - 1)
    }
}

enum class MortgageType {
    bullet, linear, annuity
}

data class MortgageSummary(val clientCode: String, val portfolioCode: String, val identifier: String, val type: MortgageType)

data class MortgageCashFlowItem(val paymentDate: LocalDate, val amount: Double)
