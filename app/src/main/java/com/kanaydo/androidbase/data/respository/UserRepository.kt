package com.kanaydo.androidbase.data.respository

import com.kanaydo.androidbase.data.network.SafeApiRequest
import com.kanaydo.androidbase.data.network.api.UserApi
import com.kanaydo.androidbase.data.params.LoginParams
import com.kanaydo.androidbase.data.responses.UserLoginResponse
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepository : SafeApiRequest(), KoinComponent {

    private val userApi: UserApi by inject()

    suspend fun loginUser(loginParams: LoginParams): UserLoginResponse {
        return apiRequest { userApi.loginEmployee(loginParams) }
    }

}