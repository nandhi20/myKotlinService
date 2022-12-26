package com.fifa

import com.fifa.db.DatabaseFactory
import com.fifa.util.securityConfiguration
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun startServer() =
    embeddedServer(Netty, port = 8088, host = "127.0.0.1") {
        DatabaseFactory.init() //Initializing database

        //Content Negotiation and Serialization
        install(ContentNegotiation) {
            jackson()
        }
        securityConfiguration()
        createRoutes()
    }
        .start(wait = true)
