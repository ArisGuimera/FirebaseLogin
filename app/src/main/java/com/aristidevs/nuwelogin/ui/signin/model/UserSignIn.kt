package com.aristidevs.nuwelogin.ui.signin.model

data class UserSignIn(
    val realName: String,
    val nickName: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String
) {
    fun isNotEmpty() =
        realName.isNotEmpty() && nickName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()
}