package com.junemon.multiplatform_masterclass.core.data.remote.source.service

import com.junemon.multiplatform_masterclass.core.data.remote.common.ApiResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class NewsSourceServiceImpl(private val httpClient: HttpClient) : NewsSourceService {

    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"

    override suspend fun fetchNewsResources(): ApiResult<NewsSourceResponse> {
        val rawResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey")
        return if (rawResponse.status.value in 200..299) {
            val newsSourceResponse = rawResponse.body<NewsSourceResponse>()
            if (newsSourceResponse.status == "ok") {
                ApiResult.Success(newsSourceResponse)
            } else {
                ApiResult.Error(message = newsSourceResponse.status)
            }
        } else {
            ApiResult.Error(
                message = rawResponse.bodyAsText(),
                code = rawResponse.status.value
            )
        }
    }
}