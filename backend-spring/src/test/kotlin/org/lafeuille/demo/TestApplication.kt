package org.lafeuille.demo

import org.springframework.boot.runApplication
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration(proxyBeanMethods = false)
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}
