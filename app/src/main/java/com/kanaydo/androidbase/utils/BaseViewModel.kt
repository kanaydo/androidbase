package com.kanaydo.androidbase.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    val sessionManager: SessionManager by inject()

    var message = MutableLiveData<String>()

    var loading = MutableLiveData<Boolean>()


    fun message(): LiveData<String> = message

    fun loading(): LiveData<Boolean> = loading
}