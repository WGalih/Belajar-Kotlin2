package com.digimaster.kotlin2.model

data class LoginResponseModel (
    // static final = val
    val status: String,
    val userModel: UserModel
)