package com.junemon.multiplatform_masterclass.core.data.articles.remote.dataSource

import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ApiResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticleData
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticlesResponse
import com.junemon.multiplatform_masterclass.core.data.articles.remote.service.ArticleService

class ArticleRemoteDataSourceImpl(private val service: ArticleService) : ArticleRemoteDataSource {

    override suspend fun getArticles(): DataResult<List<ArticleData>> {
        return when (val remoteData = service.fetchArticles()) {
            is ApiResult.Error -> DataResult.Error(remoteData.message)
            is ApiResult.Success<ArticlesResponse> -> DataResult.Data(remoteData.data.articles)
        }
    }
}