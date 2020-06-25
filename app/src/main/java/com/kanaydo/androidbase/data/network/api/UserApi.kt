package com.kanaydo.androidbase.data.network.api

import com.kanaydo.androidbase.data.params.LoginParams
import com.kanaydo.androidbase.data.responses.UserLoginResponse
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.POST

interface UserApi {

    @POST("tokens")
    suspend fun loginEmployee(@Body loginParams: LoginParams) : Response<UserLoginResponse>

}