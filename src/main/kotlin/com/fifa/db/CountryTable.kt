package com.fifa.db

import org.jetbrains.exposed.sql.Table

object CountryTable : Table("country") {
    val id = integer("id").autoIncrement()
    val countryName = varchar("country_name", 30)
    val countryCode = varchar("country_code", 3)
    override val primaryKey = PrimaryKey(id)
}