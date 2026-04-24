package com.junemon.multiplatform_masterclass.core.data.local.article

import com.junemon.multiplatform_masterclass.core.data.remote.article.model.ArticleData

interface ArticleLocalDataSource {

    fun setLastUpdate(time: Long)

    fun getLastUpdate(): Long

    fun insertArticles(articles: List<ArticleData>)

    fun getArticles(): List<ArticleData>

    fun getArticlesCount(): Long

    fun deleteArticles()
}