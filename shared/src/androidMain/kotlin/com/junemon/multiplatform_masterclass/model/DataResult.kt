package com.junemon.multiplatform_masterclass.model

sealed interface DataResult<out T> {
    object Loading : DataResult<Nothing>

    data class Data<out T>(val data: T) : DataResult<T>

    data class Error(val message: String) : DataResult<Nothing>
}