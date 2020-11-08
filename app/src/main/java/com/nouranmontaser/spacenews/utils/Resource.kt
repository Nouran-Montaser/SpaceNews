package com.nouranmontaser.spacenews.utils

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T?) : Resource<T>()
    data class Error<out T : Any>(val data: T?, val errorMsg: Int) : Resource<T>()
    object Loading : Resource<Nothing>()
}

inline fun <T : Any> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) data?.let { action(it) }
    return this
}

inline fun <T : Any> Resource<T>.onError(action: (T?, Int) -> Unit): Resource<T> {
    if (this is Resource.Error)
        action(this.data, this.errorMsg)
    return this
}

inline fun <T : Any> Resource<T>.loading(action: () -> Unit): Resource<T> {
    if (this is Resource.Loading)
        action()
    return this
}
