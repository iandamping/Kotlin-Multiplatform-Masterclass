package com.junemon.multiplatform_masterclass.core.data.common

sealed interface DomainResult<out T> {
    object Loading : DomainResult<Nothing>

    data class Data<out T>(val data: T) : DomainResult<T>

    data class Error(val message: String) : DomainResult<Nothing>
}