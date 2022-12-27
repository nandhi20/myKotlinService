package com.fifa.util

import com.fifa.service.CountryService
import com.fifa.service.impl.CountryServiceImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.securityConfiguration() {
    JWTConfig.init("MyService")
    val service: CountryService = CountryServiceImpl()
    install(Authentication) {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAIM).asString()
                if (claim != null && service.findCountryByCode(claim) != null) {
                    CountryCodePrincipal(claim)
                } else null
            }
        }
    }
}