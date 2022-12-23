package com.fifa

import com.fifa.Repository.CountryRepository
import com.fifa.Repository.Impl.CountryRepositoryImpl
import com.fifa.db.DatabaseFactory
import com.fifa.service.CountryService
import com.fifa.service.impl.CountryServiceImpl
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*


fun main() {
    embeddedServer(Tomcat, port = 8088, host = "127.0.0.1") {

        DatabaseFactory.init() //Initializing database

        val service: CountryService = CountryServiceImpl()
        val repository: CountryRepository = CountryRepositoryImpl(service)
//        countryRoutes(repository)
//        authRoutes(repository)
    }
        .start(wait = true)
}




