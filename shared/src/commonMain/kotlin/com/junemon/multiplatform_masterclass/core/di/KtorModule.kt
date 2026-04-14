package com.junemon.multiplatform_masterclass.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.*
import org.koin.dsl.module

val ktorModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println("KTOR_LOG: $message")
                    }
                }
                level = LogLevel.BODY
            }
        }
    }
}

