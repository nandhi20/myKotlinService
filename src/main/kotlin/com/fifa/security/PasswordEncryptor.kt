package com.fifa.security

import com.fifa.util.ApplicationConstant
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val SECRET_KEY = ApplicationConstant.SECRET
private val ALGORITHM = ApplicationConstant.ALGORITHM

private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun encoder(input: String): String {
    val hmac = Mac.getInstance(ALGORITHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(input.toByteArray(Charsets.UTF_8)))
}