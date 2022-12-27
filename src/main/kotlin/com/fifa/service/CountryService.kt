package com.fifa.service

import com.fifa.models.Country
import com.fifa.models.CountryParams

interface CountryService {
    suspend fun registerCountry(params: CountryParams): Country?
    suspend fun findCountryByName(countryName: String): Country?
    suspend fun findCountryByCode(countryCode: String): Country?
    suspend fun getCountries(): List<Country?>
    suspend fun removeCountry(code: String): Int
}