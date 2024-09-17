package com.tiger.weatherforecast.repository.model

import retrofit2.HttpException
import retrofit2.Response

sealed class ResultWrapper<out T> {
    class Success<T : Any>(val data: T) : ResultWrapper<T>()
    class Error<T : Any>(val code: Int, val message: String?) : ResultWrapper<T>()
    class Exception<T : Any>(val e: Throwable) : ResultWrapper<T>()
}

suspend fun <T : Any?> handleApi(
    execute: suspend () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ResultWrapper.Success(body)
        } else {
            ResultWrapper.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ResultWrapper.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ResultWrapper.Exception(e)
    }
}