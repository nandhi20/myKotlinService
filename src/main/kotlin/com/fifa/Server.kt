package com.fifa

import com.fifa.db.DatabaseFactory
import com.fifa.security.securityConfiguration
import com.fifa.util.ApplicationConstant
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun startServer() =
    embeddedServer(Netty, port = 8088, host = ApplicationConstant.HOST) {
        DatabaseFactory.init() //Initializing database

        //Content Negotiation and Serialization
        install(ContentNegotiation) {
            jackson()
        }
        securityConfiguration()
        createRoutes()
    }
        .start(wait = true)
