package com.junemon.multiplatform_masterclass.core.data.articles.repository

import com.junemon.multiplatform_masterclass.core.data.articles.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.Article

interface ArticleRepository {

    suspend fun getArticles(): DataResult<List<Article>>
}