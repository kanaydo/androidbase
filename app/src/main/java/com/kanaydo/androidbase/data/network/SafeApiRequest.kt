package com.kanaydo.androidbase.data.network

import com.kanaydo.androidbase.data.responses.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val errorMessage = StringBuilder()
            try {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse? =
                    gson.fromJson(response.errorBody()?.charStream(), type)
                errorMessage.append(errorResponse?.message)
            } catch (e: Exception) {
                errorMessage.append("Something went wrong!!")
            }
            throw ApiBaseException("$errorMessage - code: ${response.code()}")
        }
    }
}

open class ApiBaseException(message: String) : IOException(message)