package com.junemon.multiplatform_masterclass.articles.service

import com.junemon.multiplatform_masterclass.articles.model.ApiResult
import com.junemon.multiplatform_masterclass.articles.model.ArticlesResponse

interface ArticleService {

    suspend fun fetchArticles(): ApiResult<ArticlesResponse>
}