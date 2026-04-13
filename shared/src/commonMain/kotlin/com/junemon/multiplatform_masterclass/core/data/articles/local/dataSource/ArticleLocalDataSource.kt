package com.junemon.multiplatform_masterclass.core.data.articles.local.dataSource

import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticleData

interface ArticleLocalDataSource {

    fun setLastUpdate(time: Long)

    fun getLastUpdate(): Long

    fun insertArticles(articles: List<ArticleData>)

    fun getArticles(): List<ArticleData>

    fun getArticlesCount(): Long

    fun deleteArticles()
}