package com.kanaydo.androidbase.di

import android.content.Context
import com.kanaydo.androidbase.data.network.NetworkBase
import com.kanaydo.androidbase.data.network.api.UserApi
import com.kanaydo.androidbase.utils.SessionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { provideNetwork(androidContext()) }
    single { provideUserApi(get()) }
    single { SessionManager(androidContext()) }
}

fun provideNetwork(context: Context): Retrofit = NetworkBase(context).connect()

fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)