package com.junemon.multiplatform_masterclass.core.data.local.source

import com.junemon.multiplatform_masterclass.core.data.remote.source.model.NewsSourceData

interface NewsSourceLocalDataSource {

    fun setLastUpdate(time: Long)

    fun getLastUpdate(): Long

    fun insertNewsSources(sources: List<NewsSourceData>)

    fun getNewsSources(): List<NewsSourceData>

    fun getNewsSourcesCount(): Long

    fun deleteNewsSources()
}