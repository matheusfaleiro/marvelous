package dev.theuzfaleiro.network.usecase.base

sealed class ResultStatus<out T> {
    object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val exception: Throwable) : ResultStatus<Nothing>()
}