package com.kanaydo.androidbase.data.network

import android.content.Context
import com.kanaydo.androidbase.BuildConfig
import com.kanaydo.androidbase.data.network.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkBase(
    context: Context
) {

    private val interceptor: NetworkInterceptor = NetworkInterceptor(context)

    /**
     * intercept any network request to back-end service, just for development purpose only
     */
    private fun getInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * build and return new retrofit instance using OkHttpClient interceptor and GSON as convert factory
     */
    fun connect(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getUserApi(): UserApi = connect().create(UserApi::class.java)
}