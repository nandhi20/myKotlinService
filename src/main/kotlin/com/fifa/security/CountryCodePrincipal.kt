package com.fifa.security

import io.ktor.server.auth.*

data class CountryCodePrincipal(val code: String) : Principal
