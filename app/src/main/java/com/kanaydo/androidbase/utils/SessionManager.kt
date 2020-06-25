package com.kanaydo.androidbase.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.kanaydo.androidbase.data.model.User
import com.kanaydo.androidbase.ui.login.LoginActivity
import com.kanaydo.androidbase.ui.main.MainActivity

class SessionManager(context: Context) {

    var applicationContext: Context

    companion object {
        private const val PREFERENCES_NAME = "BASE.PREFERENCES_NAME"
        private const val SAVED_USER_ID_KEY = "$PREFERENCES_NAME.SAVED_USER_ID_KEY"
        private const val IS_LOGIN_KEY = "$PREFERENCES_NAME.IS_LOGIN_KEY"
        private const val PRIVATE_MODE = 0
    }


    private var preferences: SharedPreferences


    init {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, PRIVATE_MODE)
        this.applicationContext = context
    }

    fun activeUser() : Int {
        return preferences.getInt(SAVED_USER_ID_KEY, 0)
    }

    fun login(user: User) {
        with(preferences.edit()){
            putInt(SAVED_USER_ID_KEY, user.id)
            putBoolean(IS_LOGIN_KEY, true)
            commit()
        }
        val intent = Intent(applicationContext, MainActivity::class.java)
        with(intent){
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        applicationContext.startActivity(intent)
    }

    fun checkLogin() {
        if (this.isLoggedIn()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
        }
    }

    private fun isLoggedIn() : Boolean {
        return preferences.getBoolean(IS_LOGIN_KEY, false)
    }


    fun logout() {
        with(preferences.edit()){
            clear()
            commit()
        }
        val intent = Intent(applicationContext, LoginActivity::class.java)
        with(intent){
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        applicationContext.startActivity(intent)
    }




}