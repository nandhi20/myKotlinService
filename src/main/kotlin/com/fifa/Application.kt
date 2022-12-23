package com.fifa

import com.fifa.db.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import com.fifa.plugins.*

fun main() {
    embeddedServer(Tomcat, port = 8088, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init() //Initializing database
    configureRouting()
}
