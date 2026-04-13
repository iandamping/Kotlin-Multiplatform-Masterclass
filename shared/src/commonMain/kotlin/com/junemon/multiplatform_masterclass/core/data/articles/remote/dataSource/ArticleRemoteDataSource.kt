package com.junemon.multiplatform_masterclass.core.data.articles.remote.dataSource

import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticleData

interface ArticleRemoteDataSource {

    suspend fun getArticles(): DataResult<List<ArticleData>>
}