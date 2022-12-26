package com.fifa.service.impl

import com.fifa.db.CountryTable
import com.fifa.db.DatabaseFactory.dbQuery
import com.fifa.models.Country
import com.fifa.models.CountryParams
import com.fifa.service.CountryService
import com.fifa.util.JWTConfig
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement

class CountryServiceImpl : CountryService {
    override suspend fun registerCountry(params: CountryParams): Country? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = CountryTable.insert {
                it[countryName] = params.countryName
                it[countryCode] = params.countryCode
            }
        }
        return mapToCountry(statement?.resultedValues?.get(0))
    }

    override suspend fun getCountries(): List<Country?> {
        return dbQuery { CountryTable.selectAll().map { mapToCountry(it) } }
    }

    override suspend fun findCountryByName(countryName: String): Country? {
        return dbQuery {
            CountryTable.select { CountryTable.countryName.eq(countryName) }.map { mapToCountry(it) }.singleOrNull()
        }
    }

    private fun mapToCountry(row: ResultRow?): Country? {
        return if (row == null) null
        else Country(
            id = row[CountryTable.id],
            countryCode = row[CountryTable.countryCode],
            countryName = row[CountryTable.countryName],
            token = JWTConfig.instance.createToken(row[CountryTable.countryCode])
        )
    }
}