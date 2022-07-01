package com.nitin.cleanarch.feature_one.core

sealed class ViewStateResource<out T> {
    object ViewLoading : ViewStateResource<Nothing>()
    data class ViewError(val errorResponse: String) : ViewStateResource<Nothing>()
    data class ViewSuccess<T>(val item: T) : ViewStateResource<T>()
}
