package nl.freekm.mgtest.mortgage

import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * Service for computing the cash flow of mortgages.
 */
@Service
class CashFlowService {
    private val cashFlowStartDate = LocalDate.parse("2020-07-01")

    /**
     * Computes the cash flow for a provided mortgage, starting at 2020-07-01 until the end date of the mortgage.
     * @param {@link Mortgage} to compute the cash flow for.
     * @return A list of {@link CashFlowItem} objects for every payment in the mortgage cash flow.
     */
    fun computeCashFlow(mortgage: Mortgage): List<MortgageCashFlowItem> {
        return when(mortgage.type) {
            MortgageType.annuity -> this.computeCashFlowAnnuity(mortgage)
            MortgageType.bullet -> this.computeCashFlowBullet(mortgage)
            MortgageType.linear -> this.computeCashFlowLinear(mortgage)
        }
    }

    /**
     * Computes the cash flow of a mortgage of type annuity, where the monthly payment is fixed and the ratio between
     * repayment and interest paid depends on the remaining notional.
     * At the end of the mortgage period, any remaining notional is paid at once.
     */
    private fun computeCashFlowAnnuity(mortgage: Mortgage): List<MortgageCashFlowItem> {
        var mortgageCashFlow: MutableList<MortgageCashFlowItem> = mutableListOf()
        var paymentDate = cashFlowStartDate
        var notional = mortgage.currentNotional

        while (paymentDate < mortgage.endDate) {
            val interest = (notional * mortgage.monthlyInterestRate())

            mortgageCashFlow.add(MortgageCashFlowItem(paymentDate, mortgage.currentMonthlyAnnuity))

            notional -= (mortgage.currentMonthlyAnnuity - interest)
            paymentDate = paymentDate.plusMonths(1)
        }
        if (notional > 0) {
            mortgageCashFlow.add(MortgageCashFlowItem(mortgage.endDate, notional))
        }
        return mortgageCashFlow.toList()
    }

    /**
     * Computes the cash flow of a mortgage of type bullet, where the monthly payment is only the interest due over the
     * current month, and the notional remains unchanged throughout the entire mortgage period.
     * At the end of the mortgage period, the remaining notional is paid at once.
     */
    private fun computeCashFlowBullet(mortgage: Mortgage): List<MortgageCashFlowItem> {
        var mortgageCashFlow: MutableList<MortgageCashFlowItem> = mutableListOf()
        var paymentDate = cashFlowStartDate
        while (paymentDate < mortgage.endDate) {
            mortgageCashFlow.add(MortgageCashFlowItem(paymentDate, mortgage.currentMonthlyInterest))
            paymentDate = paymentDate.plusMonths(1)
        }
        mortgageCashFlow.add(MortgageCashFlowItem(mortgage.endDate, mortgage.currentNotional))
        return mortgageCashFlow.toList()
    }

    /**
     * Computes the cash flow of a mortgage of type linear, where the monthly is the sum of a fixed repayment and the
     * interest due over the remaining notional.
     * At the end of the mortgage period, any remaining notional is paid at once.
     */
    private fun computeCashFlowLinear(mortgage: Mortgage): List<MortgageCashFlowItem> {
        var mortgageCashFlow: MutableList<MortgageCashFlowItem> = mutableListOf()
        var paymentDate = cashFlowStartDate
        var notional = mortgage.currentNotional

        while (paymentDate < mortgage.endDate) {
            val interest = notional * mortgage.monthlyInterestRate()
            mortgageCashFlow.add(MortgageCashFlowItem(paymentDate, interest + mortgage.currentLinearRepayment))
            notional -= mortgage.currentLinearRepayment
            paymentDate = paymentDate.plusMonths(1)
        }

        if (notional > 0) {
            mortgageCashFlow.add(MortgageCashFlowItem(mortgage.endDate, notional))
        }
        return mortgageCashFlow.toList()
    }

}
