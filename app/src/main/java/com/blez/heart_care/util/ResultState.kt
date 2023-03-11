package com.blez.heart_care.util

sealed class ResultState<out T> {

    data class Success<out R>(val data:R) : ResultState<R>()
    data class Failure(val msg:Throwable) : ResultState<Nothing>()

    object Loading : ResultState<Nothing>()

}