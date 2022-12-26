package com.fifa.Repository.Impl

import com.fifa.Repository.CountryRepository
import com.fifa.models.CountryParams
import com.fifa.models.Response
import com.fifa.service.CountryService
import io.ktor.server.plugins.*


class CountryRepositoryImpl(private val countryService: CountryService) : CountryRepository {
    override suspend fun registerCountry(params: CountryParams): Response<Any> {
        return if (isCountryExists(params.countryName)) {
            Response.ErrorResponse(
                exception = BadRequestException("400"),
                message = "Country name already found"
            )
        } else {
            Response.SuccessResponse(
                data = countryService.registerCountry(params),
                message = "Country registered successfully"
            )
        }

    }

    override suspend fun getCountries(): Response<Any> {
        return Response.SuccessResponse(
            data = countryService.getCountries(),
            message = "Country registered successfully"
        )
    }

    private suspend fun isCountryExists(countryName: String): Boolean =
        countryService.findCountryByName(countryName) != null
}