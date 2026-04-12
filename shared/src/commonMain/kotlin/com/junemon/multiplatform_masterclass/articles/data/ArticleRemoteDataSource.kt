package com.junemon.multiplatform_masterclass.articles.data

import com.junemon.multiplatform_masterclass.articles.common.DataResult
import com.junemon.multiplatform_masterclass.articles.model.ArticleData

interface ArticleRemoteDataSource {

    suspend fun getArticles(): DataResult<List<ArticleData>>
}