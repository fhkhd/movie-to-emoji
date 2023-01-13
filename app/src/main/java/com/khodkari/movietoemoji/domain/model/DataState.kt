package com.khodkari.movietoemoji.domain.model

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(var error: String) : DataState<Nothing>()
}