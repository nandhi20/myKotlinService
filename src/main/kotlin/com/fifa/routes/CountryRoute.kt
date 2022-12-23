package com.fifa.routes

import com.fifa.Repository.CountryRepository
import com.fifa.models.CountryParams
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.countryRoutes(repository: CountryRepository): Unit {
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
