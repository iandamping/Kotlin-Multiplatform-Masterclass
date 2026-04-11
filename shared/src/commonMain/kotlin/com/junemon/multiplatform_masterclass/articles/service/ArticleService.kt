package com.junemon.multiplatform_masterclass.articles.service

import com.junemon.multiplatform_masterclass.articles.model.ApiResult
import com.junemon.multiplatform_masterclass.articles.model.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class ArticleService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"

    //    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"
    private val apiKey = "25bd4439e7564164a9ab567975428415"

    suspend fun fetchArticles(): ApiResult<ArticlesResponse> {
        val rawResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
        return if (rawResponse.status.value in 200..299) {
            val articleResponse = rawResponse.body<ArticlesResponse>()
            if (articleResponse.status == "ok") {
                ApiResult.Success(articleResponse)
            } else ApiResult.Error(message = articleResponse.status)
        } else {
            ApiResult.Error(
                message = rawResponse.bodyAsText(),
                code = rawResponse.status.value
            )
        }
    }
}