package com.fifa.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JWTConfig private constructor(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)

    companion object {
        private const val ISSUER = "MyService"
        private const val AUDIENCE = "MyService"
        const val CLAIM = "code"

        lateinit var instance: JWTConfig
            private set

        fun init(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JWTConfig(secret)
                }
            }
        }
    }

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withAudience(AUDIENCE)
        .withIssuer(ISSUER)
        .build()

    fun createToken(code: String): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, code)
        .sign(algorithm)
}