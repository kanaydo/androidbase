package com.kanaydo.androidbase.data.respository

import android.app.Application
import com.kanaydo.androidbase.data.network.NetworkBase
import com.kanaydo.androidbase.data.network.SafeApiRequest
import com.kanaydo.androidbase.data.params.LoginParams
import com.kanaydo.androidbase.data.responses.UserLoginResponse

class UserRepository(
    application: Application
) : SafeApiRequest() {

    private val networkBase = NetworkBase(application)
    private val userApi = networkBase.getUserApi()


    suspend fun loginUser(loginParams: LoginParams) : UserLoginResponse {
        return apiRequest { userApi.loginEmployee(loginParams) }
    }

}