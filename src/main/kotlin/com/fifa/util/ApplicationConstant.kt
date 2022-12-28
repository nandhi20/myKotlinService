package com.fifa.util

class ApplicationConstant {

    companion object {
        const val DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
        const val DATABASE_PASSWORD = "Nandy"
        const val DATABASE_USERNAME = "postgres"
        const val DATABASE_TRANSACTION  = "TRANSACTION_REPEATABLE_READ"
        const val DATABASE_DRIVER = "org.postgresql.Driver"
        const val HOST = "127.0.0.1"
        const val SECRET = "MyService"
        const val ALGORITHM = "HmacSHA1"
        const val CLAIM = "code"
    }
}