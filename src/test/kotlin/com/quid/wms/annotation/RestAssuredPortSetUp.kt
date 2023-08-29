package com.quid.wms.annotation

import io.restassured.RestAssured
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.test.context.junit.jupiter.SpringExtension

class RestAssuredPortSetUp : BeforeEachCallback {
    override fun beforeEach(context: ExtensionContext) {
        val applicationContext = SpringExtension.getApplicationContext(context)
        val serverPort = applicationContext.environment.getProperty("local.server.port")?.toInt()?:0
        RestAssured.port = serverPort
    }
}