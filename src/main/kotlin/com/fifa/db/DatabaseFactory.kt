package com.fifa.db

import com.fifa.util.ApplicationConstant
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        val database = Database.connect(dataSource())

        transaction(database) {
            SchemaUtils.create(CountryTable)
        }
    }

    private fun dataSource(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = ApplicationConstant.DATABASE_DRIVER
        config.jdbcUrl = ApplicationConstant.DATABASE_URL
        config.password = ApplicationConstant.DATABASE_PASSWORD
        config.username = ApplicationConstant.DATABASE_USERNAME
//        config.password = "nandy"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = ApplicationConstant.DATABASE_TRANSACTION
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}