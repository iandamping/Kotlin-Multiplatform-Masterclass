package com.junemon.multiplatform_masterclass.core.data.articles.remote.model

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>

    data class Error(
        val message: String,
        val code: Int? = null,
    ) : ApiResult<Nothing>
}