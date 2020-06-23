package com.kanaydo.androidbase.di
import android.content.Context
import com.kanaydo.androidbase.data.network.NetworkBase
import com.kanaydo.androidbase.data.network.api.UserApi
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import org.koin.dsl.module



val appModule = module {
    single {
        provideNetwork(androidContext())
    }
    single {
        provideUserApi(get())
    }
}

fun provideNetwork(context: Context) : Retrofit = NetworkBase(context).connect()

fun provideUserApi(retrofit: Retrofit) : UserApi = retrofit.create(UserApi::class.java)