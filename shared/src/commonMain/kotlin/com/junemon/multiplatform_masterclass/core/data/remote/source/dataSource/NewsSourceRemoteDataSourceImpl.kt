package com.junemon.multiplatform_masterclass.core.data.remote.source.dataSource

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.common.ApiResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceData
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceResponse
import com.junemon.multiplatform_masterclass.core.data.remote.source.service.NewsSourceService

class NewsSourceRemoteDataSourceImpl(private val newsService: NewsSourceService) :
    NewsSourceRemoteDataSource {

    override suspend fun getNewsResource(): DataResult<List<NewsSourceData>> {
        return when (val remoteData = newsService.fetchNewsResources()) {
            is ApiResult.Error -> DataResult.Error(remoteData.message)
            is ApiResult.Success<NewsSourceResponse> -> DataResult.Data(remoteData.data.newsSources)
        }
    }
}