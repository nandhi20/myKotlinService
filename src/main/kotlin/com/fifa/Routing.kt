package com.fifa

import com.fifa.Repository.CountryRepository
import com.fifa.Repository.Impl.CountryRepositoryImpl
import com.fifa.models.CountryParams
import com.fifa.service.CountryService
import com.fifa.service.impl.CountryServiceImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.createRoutes() {
    val service: CountryService = CountryServiceImpl()
    val repository: CountryRepository = CountryRepositoryImpl(service)
    routing {
        route("/country") {
            post("/register") {
                val params = call.receive<CountryParams>()
                val result = repository.registerCountry(params)
                call.respond(result.statusCode, result)
            }
            authenticate {
                get("/") {
                    val result = repository.getCountries()
                    call.respond(result.statusCode, result)
                }
                delete("/{countryCode}") {
                    val result = repository.removeCountry(call.parameters["countryCode"])
                    call.respond(result.statusCode, result)
                }
                put("/{countryId}") {
                    val result = repository.updateCountry(call.receive<CountryParams>(), call.parameters["countryId"])
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}