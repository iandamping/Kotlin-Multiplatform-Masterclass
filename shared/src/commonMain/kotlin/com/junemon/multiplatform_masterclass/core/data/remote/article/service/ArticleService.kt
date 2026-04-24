package com.junemon.multiplatform_masterclass.core.data.remote.article.service

import com.junemon.multiplatform_masterclass.core.data.remote.common.ApiResult
import com.junemon.multiplatform_masterclass.core.data.remote.article.model.ArticlesResponse

interface ArticleService {

    suspend fun fetchArticles(): ApiResult<ArticlesResponse>
}