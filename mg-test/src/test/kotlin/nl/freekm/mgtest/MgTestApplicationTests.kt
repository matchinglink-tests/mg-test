package nl.freekm.mgtest

import com.fasterxml.jackson.databind.JsonNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MgTestApplicationTests(@Autowired val testRestTemplate: TestRestTemplate) {

	@Test
	fun contextLoads() {
		assertThat(testRestTemplate).isNotNull
	}

	@Test
	fun testMortgageSummaryList() {
		val result = testRestTemplate.getForEntity("/mortgage", JsonNode::class.java)
		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(15, result.body?.size())
	}

	@Test
	fun testMortgageDetails() {
		val result = testRestTemplate.getForEntity("/mortgage/HYP001", JsonNode::class.java)
		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
	}

	@Test
	fun testMortgageDetailsInvalidIdentifier() {
		val result = testRestTemplate.getForEntity("/mortgage/HYPXXX", JsonNode::class.java)
		assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
	}

	@Test
	fun testCashFlow() {
		val result = testRestTemplate.getForEntity("/mortgage/HYP001/cashflow", JsonNode::class.java)
		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(298, result.body?.size())
	}

	@Test
	fun testCashFlowInvalidIdentifier() {
		val result = testRestTemplate.getForEntity("/mortgage/HYPXXX/cashflow", JsonNode::class.java)
		assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
	}

}
