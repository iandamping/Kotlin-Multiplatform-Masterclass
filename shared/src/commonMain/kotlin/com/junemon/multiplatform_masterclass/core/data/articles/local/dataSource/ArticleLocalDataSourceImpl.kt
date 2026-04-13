package com.junemon.multiplatform_masterclass.core.data.articles.local.dataSource

import com.junemon.multiplatform_masterclass.core.data.articles.remote.model.ArticleData
import com.junemon.multiplatform_masterclass.db.MultiplatformMasterclassDatabase
import com.russhwolf.settings.Settings

class ArticleLocalDataSourceImpl(
    private val database: MultiplatformMasterclassDatabase,
    private val sharedPreferences: Settings
) :
    ArticleLocalDataSource {

    private val getLastUpdateKey = "update"

    override fun setLastUpdate(time: Long) {
        sharedPreferences.putLong(getLastUpdateKey, time)
    }

    override fun getLastUpdate(): Long {
        return sharedPreferences.getLong(getLastUpdateKey, 0L)
    }

    override fun insertArticles(articles: List<ArticleData>) {
        articles.forEach { article ->
            database.multiplatformMasterclassDatabaseQueries.insertArticle(
                title = article.title,
                desc = article.desc,
                date = article.date,
                imageUrl = article.imageUrl
            )
        }

    }

    override fun getArticles(): List<ArticleData> {
        return database.multiplatformMasterclassDatabaseQueries.selectAllArticles { title, desc, date, imageUrl ->
            ArticleData(
                title = title,
                desc = desc,
                date = date,
                imageUrl = imageUrl
            )
        }.executeAsList()

    }

    override fun getArticlesCount(): Long {
        return database.multiplatformMasterclassDatabaseQueries.getArticlesCount().executeAsOne()
    }

    override fun deleteArticles() {
        database.multiplatformMasterclassDatabaseQueries.removeAllArticles()
    }
}