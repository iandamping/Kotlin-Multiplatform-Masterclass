package com.junemon.multiplatform_masterclass.core.data.remote.source.service

import com.junemon.multiplatform_masterclass.core.data.remote.common.ApiResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceResponse

interface NewsSourceService {

    suspend fun fetchNewsResources(): ApiResult<NewsSourceResponse>
}