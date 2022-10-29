package com.example.vix_schoters_taufikdito.data

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String): Result<Nothing>()
    object Loading : Result<Nothing>()
}