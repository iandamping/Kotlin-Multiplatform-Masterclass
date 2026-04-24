package com.junemon.multiplatform_masterclass.core.data.remote.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsSourceData(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val desc: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String,
)
