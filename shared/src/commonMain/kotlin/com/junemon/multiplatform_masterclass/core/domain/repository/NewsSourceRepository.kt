package com.junemon.multiplatform_masterclass.core.domain.repository

import com.junemon.multiplatform_masterclass.core.data.common.DataResult
import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSource

interface NewsSourceRepository {

    suspend fun getNewsSource(isForceRefresh:Boolean): DataResult<List<NewsSource>>

}