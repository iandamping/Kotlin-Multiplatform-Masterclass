package com.junemon.multiplatform_masterclass.core.data.remote.article.dataSource

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.ArticleData

interface ArticleRemoteDataSource {

    suspend fun getArticles(): DataResult<List<ArticleData>>
}