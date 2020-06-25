package com.kanaydo.androidbase.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

class NetworkInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connected()) throw ApiBaseException("Not connected to network, Please check your internet connection before try again!")
        try {
            return chain.proceed(chain.request())
        } catch (conn_exception: ConnectException) {
            throw ApiBaseException("Not connected to server, Please contact your server provider!")
        } catch (rto_exception: SocketTimeoutException) {
            throw ApiBaseException("Request Timeout, please try again!")
        }
    }


    private fun connected(): Boolean {
        val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        conn.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}