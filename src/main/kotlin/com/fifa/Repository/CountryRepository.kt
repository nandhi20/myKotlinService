package com.fifa.Repository

import com.fifa.models.CountryParams
import com.fifa.models.Response

interface CountryRepository {
    suspend fun registerCountry(params: CountryParams): Response<Any>
    suspend fun getCountries(): Response<Any>
}