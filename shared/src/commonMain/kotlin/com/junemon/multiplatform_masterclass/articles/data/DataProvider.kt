package com.junemon.multiplatform_masterclass.articles.data

import com.junemon.multiplatform_masterclass.articles.service.ArticleService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object DataProvider {
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    fun provideArticleRemoteDataSource(httpClient: HttpClient): ArticleRemoteDataSource {
        return ArticleRemoteDataSource(ArticleService(httpClient))
    }

    fun provideArticleRepository(dataSource: ArticleRemoteDataSource): ArticleRepository {
        return ArticleRepository(dataSource)
    }
}
