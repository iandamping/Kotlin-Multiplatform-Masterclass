package com.junemon.multiplatform_masterclass.core.data.local.source

import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceData
import com.junemon.multiplatform_masterclass.db.MultiplatformMasterclassDatabase
import com.russhwolf.settings.Settings

class NewsSourceLocalDataSourceImpl(
    private val database: MultiplatformMasterclassDatabase,
    private val sharedPreferences: Settings
) : NewsSourceLocalDataSource {

    private val getLastUpdateKey = "news_source_update"

    override fun setLastUpdate(time: Long) {
        sharedPreferences.putLong(getLastUpdateKey, time)
    }

    override fun getLastUpdate(): Long {
        return sharedPreferences.getLong(getLastUpdateKey, 0L)
    }

    override fun insertNewsSources(sources: List<NewsSourceData>) {
        sources.forEach { news ->
            database.multiplatformMasterclassDatabaseQueries.insertSource(
                id = news.id,
                desc = news.desc,
                country = news.country,
                language = news.language,
                name = news.name
            )
        }
    }

    override fun getNewsSources(): List<NewsSourceData> {
        return database.multiplatformMasterclassDatabaseQueries.selectAllSources { id, name, desc, language, country ->
            NewsSourceData(
                id = id,
                name = name,
                desc = desc,
                language = language,
                country = country
            )
        }.executeAsList()
    }

    override fun getNewsSourcesCount(): Long {
        return database.multiplatformMasterclassDatabaseQueries.getSourcesCount().executeAsOne()
    }

    override fun deleteNewsSources() {
        database.multiplatformMasterclassDatabaseQueries.removeAllSources()
    }
}