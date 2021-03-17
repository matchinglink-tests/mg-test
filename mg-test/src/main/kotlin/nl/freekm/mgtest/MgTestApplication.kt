package nl.freekm.mgtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MgTestApplication

fun main(args: Array<String>) {
	runApplication<MgTestApplication>(*args)
}
