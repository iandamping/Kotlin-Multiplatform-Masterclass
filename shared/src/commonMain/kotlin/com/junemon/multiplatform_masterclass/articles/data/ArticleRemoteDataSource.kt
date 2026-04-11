package com.junemon.multiplatform_masterclass.articles.data

import com.junemon.multiplatform_masterclass.articles.common.DataResult
import com.junemon.multiplatform_masterclass.articles.model.ApiResult
import com.junemon.multiplatform_masterclass.articles.model.ArticleData
import com.junemon.multiplatform_masterclass.articles.model.ArticlesResponse
import com.junemon.multiplatform_masterclass.articles.service.ArticleService

class ArticleRemoteDataSource(private val service: ArticleService) {

    suspend fun getArticles(): DataResult<List<ArticleData>> {
        return when (val remoteData = service.fetchArticles()) {
            is ApiResult.Error -> DataResult.Error(remoteData.message)
            is ApiResult.Success<ArticlesResponse> -> DataResult.Data(remoteData.data.articles)
        }
    }
}