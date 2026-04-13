package com.junemon.multiplatform_masterclass.core.data.articles.remote.service

import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ApiResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class ArticleServiceImpl(private val httpClient: HttpClient) : ArticleService {

    private val country = "us"
    private val category = "business"

    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"

    override suspend fun fetchArticles(): ApiResult<ArticlesResponse> {
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