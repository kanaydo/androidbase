package com.kanaydo.androidbase.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kanaydo.androidbase.data.network.ApiBaseException
import com.kanaydo.androidbase.data.params.LoginParams
import com.kanaydo.androidbase.data.respository.UserRepository
import com.kanaydo.androidbase.utils.Coroutines

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var resultMessage = MutableLiveData<String>()
    private var loadingStatus = MutableLiveData<Boolean>()
    private var userRepository: UserRepository = UserRepository()

    fun validateLogin(userName: String, password: String) = Coroutines.main {
        loadingStatus.value = true
        val loginParams = LoginParams(userName, password)
        try {
            val loginResponse = userRepository.loginUser(loginParams)
            loadingStatus.value = false
        } catch (e: ApiBaseException){
            loadingStatus.value = false
            resultMessage.value = e.message!!
        }

    }

    fun resultMessage() : LiveData<String> = resultMessage

    fun loadingStatus() : LiveData<Boolean> = loadingStatus

}