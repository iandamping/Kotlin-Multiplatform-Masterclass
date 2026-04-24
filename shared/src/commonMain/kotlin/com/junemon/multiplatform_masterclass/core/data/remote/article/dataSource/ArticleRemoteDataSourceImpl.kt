package com.junemon.multiplatform_masterclass.core.data.remote.article.dataSource

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.common.ApiResult
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.ArticleData
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.ArticlesResponse
import com.junemon.multiplatform_masterclass.core.data.remote.article.service.ArticleService

class ArticleRemoteDataSourceImpl(private val service: ArticleService) : ArticleRemoteDataSource {

    override suspend fun getArticles(): DataResult<List<ArticleData>> {
        return when (val remoteData = service.fetchArticles()) {
            is ApiResult.Error -> DataResult.Error(remoteData.message)
            is ApiResult.Success<ArticlesResponse> -> DataResult.Data(remoteData.data.articles)
        }
    }
}