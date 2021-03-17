package nl.freekm.mgtest.mortgage

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository
import java.io.FileInputStream
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.annotation.PostConstruct

/**
 * Data service for mortgage data.
 */
@Repository
class MortgageDataService() {
    private val EXCEL_FILE_PATH = "classpath:mortgage_data.xlsx"
    private val EXCEL_SHEET = "mortgage_loans"

    private var mortgageList: MutableList<Mortgage> = mutableListOf()

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    /**
     * Reads the contents of the excel file with mortgages.
     */
    @PostConstruct
    fun loadMortgageData() {
        val excelFile = this.resourceLoader.getResource(EXCEL_FILE_PATH).file
        val workbook = XSSFWorkbook(FileInputStream(excelFile))
        val sheet = workbook.getSheet(EXCEL_SHEET)
        val rows = sheet.iterator()
        for (row in rows) {
            if (row.rowNum != 0) {
                mortgageList.add(this.mapExcelRowToMortgage(row))
            }
        }
    }

    /**
     * Returns the full list of mortgages read from an excel file on initialization.
     * @return a list of {@link Mortgage} objects.
     */
    fun list(): List<Mortgage> {
        return this.mortgageList
    }

    /**
     * Returns the mortgage associated with the provided identifier.
     * @param identifier, the identifier to return the mortgage for.
     * @return A {@link Mortgage} matching the provided identifier or null if no mortgage matches the given identifier.
     */
    fun findById(identifier: String): Mortgage? {
        return this.mortgageList.find{ mortgage -> mortgage.identifier == identifier}
    }

    private fun mapExcelRowToMortgage(row: Row): Mortgage {
        return Mortgage(
            clientCode = row.getCell(0).stringCellValue,
            portfolioCode = row.getCell(1).stringCellValue,
            identifier = row.getCell(2).stringCellValue,
            originalNotional = row.getCell(3).numericCellValue,
            type = MortgageType.valueOf(row.getCell(4).stringCellValue),
            currentNotional = row.getCell(5).numericCellValue,
            startDate = row.getCell(6).dateCellValue.toLocalDate(),
            endDate = row.getCell(7).dateCellValue.toLocalDate(),
            interestRate = row.getCell(8).numericCellValue,
            fixedUntil = row.getCell(9).dateCellValue.toLocalDate(),
            currentMonthlyInterest = row.getCell(10).numericCellValue,
            currentMonthlyAnnuity = row.getCell(11).numericCellValue,
            currentLinearRepayment = row.getCell(12).numericCellValue,
            currentPolicyPremium = row.getCell(13).numericCellValue,
            propertyValue = row.getCell(14).numericCellValue,
            propertyValueDate = if (row.getCell(15) != null) row.getCell(15).dateCellValue.toLocalDate() else null,
            properLiquidationValue = row.getCell(16).numericCellValue,
            policyAndOtherValue = row.getCell(17).numericCellValue,
            postalCode = row.getCell(18)?.stringCellValue,
            isNhg = row.getCell(19).booleanCellValue,
        )
    }

    private fun Date.toLocalDate(): LocalDate {
        return this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
