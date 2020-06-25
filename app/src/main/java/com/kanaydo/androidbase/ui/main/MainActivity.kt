package com.kanaydo.androidbase.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kanaydo.androidbase.R
import com.kanaydo.androidbase.utils.SessionManager
import org.koin.android.ext.android.inject
import org.koin.core.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}