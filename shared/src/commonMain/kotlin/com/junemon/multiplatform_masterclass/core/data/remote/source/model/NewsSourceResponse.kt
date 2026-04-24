package com.junemon.multiplatform_masterclass.core.data.remote.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsSourceResponse(
    @SerialName("status")
    val status: String,
    @SerialName("sources")
    val newsSources: List<NewsSourceData>,
)
