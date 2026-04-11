package com.junemon.multiplatform_masterclass.articles.common

sealed interface DataResult<out T> {

    data class Data<out T>(val data: T) : DataResult<T>

    data class Error(val message: String) : DataResult<Nothing>
}