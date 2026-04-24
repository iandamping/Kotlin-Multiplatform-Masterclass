package com.junemon.multiplatform_masterclass.core.domain.repository

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.local.source.NewsSourceLocalDataSource
import com.junemon.multiplatform_masterclass.core.data.remote.source.dataSource.NewsSourceRemoteDataSource
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSource
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceData
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.hours

class NewsSourceRepositoryImpl(
    private val remoteData: NewsSourceRemoteDataSource,
    private val localData: NewsSourceLocalDataSource
) :
    NewsSourceRepository {

    private val databaseTimeout = 24.hours.inWholeMilliseconds

    private fun isLocalDataExpired(): Boolean {
        val count = localData.getNewsSourcesCount()
        val lastSync = localData.getLastUpdate()

        return count == 0L || (Clock.System.now()
            .toEpochMilliseconds() - lastSync) > databaseTimeout
    }

    override suspend fun getNewsSource(isForceRefresh: Boolean): DataResult<List<NewsSource>> {
        return if (isLocalDataExpired()) {
            when (val remoteData = remoteData.getNewsResource()) {
                is DataResult.Data<List<NewsSourceData>> -> {
                    localData.insertNewsSources(remoteData.data)
                    localData.setLastUpdate(Clock.System.now().toEpochMilliseconds())

                    DataResult.Data(newsSourceMapper(localData.getNewsSources()))
                }

                is DataResult.Error -> DataResult.Error(remoteData.message)
            }
        } else {
            if (isForceRefresh) {
                when (val remoteData = remoteData.getNewsResource()) {
                    is DataResult.Data<List<NewsSourceData>> -> {
                        localData.deleteNewsSources()
                        localData.insertNewsSources(remoteData.data)
                        localData.setLastUpdate(Clock.System.now().toEpochMilliseconds())

                        DataResult.Data(newsSourceMapper(localData.getNewsSources()))
                    }

                    is DataResult.Error -> DataResult.Error(remoteData.message)
                }
            } else DataResult.Data(newsSourceMapper(localData.getNewsSources()))
        }
    }

    private fun newsSourceMapper(rawData: List<NewsSourceData>): List<NewsSource> {
        return rawData.map { news ->
            NewsSource(
                id = news.id,
                name = news.name,
                desc = news.desc,
                language = news.language,
                country = news.country
            )
        }
    }

}