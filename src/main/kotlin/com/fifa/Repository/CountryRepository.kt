package com.fifa.Repository

import com.fifa.models.CountryParams
import com.fifa.models.Response

interface CountryRepository {
    suspend fun registerCountry(params: CountryParams): Response<Any>
    suspend fun getCountries(): Response<Any>
    suspend fun removeCountry(code: String?): Response<Any>
    suspend fun updateCountry(params: CountryParams, id: String?): Response<Any>
}