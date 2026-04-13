package com.junemon.multiplatform_masterclass.core.data.articles.remote.service

import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ApiResult
import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticlesResponse

interface ArticleService {

    suspend fun fetchArticles(): ApiResult<ArticlesResponse>
}