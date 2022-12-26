package com.fifa.util

import io.ktor.server.auth.*

data class CountryCodePrincipal(val code: String) : Principal
