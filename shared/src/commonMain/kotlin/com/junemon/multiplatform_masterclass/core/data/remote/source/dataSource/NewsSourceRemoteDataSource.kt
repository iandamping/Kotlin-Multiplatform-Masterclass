package com.junemon.multiplatform_masterclass.core.data.remote.source.dataSource

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceData

interface NewsSourceRemoteDataSource {

    suspend fun getNewsResource(): DataResult<List<NewsSourceData>>
}