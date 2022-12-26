package com.fifa

import com.fifa.Repository.CountryRepository
import com.fifa.Repository.Impl.CountryRepositoryImpl
import com.fifa.db.DatabaseFactory
import com.fifa.models.CountryParams
import com.fifa.service.CountryService

import com.fifa.service.impl.CountryServiceImpl
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.tomcat.*


fun main() {
    embeddedServer(Tomcat, port = 8088, host = "127.0.0.1") {

        DatabaseFactory.init() //Initializing database

        //Content Negotiation and Serialization
        install(ContentNegotiation) {
            jackson()
        }
        //Routings
        val service: CountryService = CountryServiceImpl()
        val repository: CountryRepository = CountryRepositoryImpl(service)
        routing {
            route("/country") {
                post("/register") {
                    val params = call.receive<CountryParams>()
                    val result = repository.registerCountry(params)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
        .start(wait = true)
}




