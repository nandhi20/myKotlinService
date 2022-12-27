package com.fifa.Repository.Impl

import com.fifa.Repository.CountryRepository
import com.fifa.models.Country
import com.fifa.models.CountryParams
import com.fifa.models.Response
import com.fifa.service.CountryService
import io.ktor.http.*
import io.ktor.server.plugins.*


class CountryRepositoryImpl(private val countryService: CountryService) : CountryRepository {
    override suspend fun registerCountry(params: CountryParams): Response<Any> {
        return if (isCountryExists(params.countryName)) {
            Response.ErrorResponse(
                exception = BadRequestException(HttpStatusCode.BadRequest.value.toString()),
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
            message = "Countries retrieved successfully"
        )
    }

    override suspend fun removeCountry(code: String?): Response<Any> {
        return if (code == null) {
            Response.ErrorResponse(
                exception = BadRequestException(HttpStatusCode.BadRequest.value.toString()),
                message = "Country doesn't exist"
            )
        } else {
            Response.SuccessResponse(
                data = countryService.removeCountry(code),
                message = "Country removed successfully"
            )
        }
    }

    override suspend fun updateCountry(params: CountryParams, id: String?): Response<Any> {
        return if (id == null) {

            Response.ErrorResponse(
                exception = BadRequestException(HttpStatusCode.BadRequest.value.toString()),
                message = "Country doesn't exist"
            )
        } else {
            var country: Country = Country(
                id = id.toInt(),
                countryName = params.countryName,
                countryCode = params.countryCode,
                token = null
            )
            Response.SuccessResponse(
                data = countryService.updateCountry(country),
                message = "Country updated successfully"
            )
        }
    }


    private suspend fun isCountryExists(countryName: String): Boolean =
        countryService.findCountryByName(countryName) != null

    private suspend fun isCountryExistsById(id: Int): Boolean {
        println(id)
        return countryService.fundCountryById(id) != null
    }
}