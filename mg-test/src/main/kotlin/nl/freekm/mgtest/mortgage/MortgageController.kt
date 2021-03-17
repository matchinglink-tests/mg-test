package nl.freekm.mgtest.mortgage

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("mortgage")
class MortgageController(private val mortgageDataService: MortgageDataService, private val cashFlowService: CashFlowService) {

    /**
     * Rest endpoint returning a list of mortgage instances.
     * @return A list of {@link MortgageSummary} objects.
     */
    @GetMapping("")
    fun listSummary(): List<MortgageSummary> {
        return this.mortgageDataService.list().map { mortgage -> mortgage.toSummary() }
    }

    /**
     * Rest endpoint returning the full mortgage details of the mortgage associated with the provided identifier.
     * @param id, the identifier of the mortgage to return.
     * @return A {@link Mortgage} with the provided identifier, or a status 404 (not found) if no mortgage
     * exists with the provided identifier.
     */
    @GetMapping("/{id}")
    fun getMortgage(@PathVariable id: String): ResponseEntity<Mortgage> {
        val mortgage = this.mortgageDataService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(mortgage)
    }

    /**
     * Rest endpoint returning the cash flow of the mortgage associated with the provided identifier.
     * @param id, the identifier of the mortgage to return the cash flow for.
     * @return A list of {@link CashFlowItem} related to the requested mortgage, or a status 404 (not found) if no
     * mortgage exists with the provided identifier.
     */
    @GetMapping("/{id}/cashflow")
    fun getCashFlow(@PathVariable id: String): ResponseEntity<List<MortgageCashFlowItem>> {
        val mortgage = this.mortgageDataService.findById(id) ?: return ResponseEntity.notFound().build()
        val cashFlow = this.cashFlowService.computeCashFlow(mortgage)
        return ResponseEntity.ok(cashFlow)
    }
}
