package com.kanaydo.androidbase.data.responses

import com.kanaydo.androidbase.data.model.User

data class UserLoginResponse(
    var status: Boolean,
    var user: User
)