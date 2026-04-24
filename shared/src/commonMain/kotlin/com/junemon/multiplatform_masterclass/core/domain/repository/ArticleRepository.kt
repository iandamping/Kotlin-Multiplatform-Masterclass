package com.junemon.multiplatform_masterclass.core.domain.repository

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.Article

interface ArticleRepository {

    suspend fun getArticles(isForceRefresh:Boolean): DataResult<List<Article>>
}