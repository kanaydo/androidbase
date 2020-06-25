package com.kanaydo.androidbase.ui.login

import android.app.Application
import com.kanaydo.androidbase.data.network.ApiBaseException
import com.kanaydo.androidbase.data.params.LoginParams
import com.kanaydo.androidbase.data.respository.UserRepository
import com.kanaydo.androidbase.utils.BaseViewModel
import com.kanaydo.androidbase.utils.Coroutines

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private var userRepository: UserRepository = UserRepository()

    init {
        sessionManager.checkLogin()
    }

    fun validateLogin(userName: String, password: String) = Coroutines.main {
        loading.value = true
        val loginParams = LoginParams(userName, password)
        try {
            val loginResponse = userRepository.loginUser(loginParams)
            if (loginResponse.status) {
                loading.value = false
                sessionManager.login(loginResponse.user)
            }
        } catch (e: ApiBaseException){
            loading.value = false
            message.value = e.message!!
        }

    }

}