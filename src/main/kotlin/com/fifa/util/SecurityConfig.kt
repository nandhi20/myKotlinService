package com.fifa.util

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.securityConfiguration() {
    JWTConfig.init("MyService")
    install(Authentication) {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAIM).asString()
                if (claim != null) {
                    CountryCodePrincipal(claim)
                } else null
            }
        }
    }
}