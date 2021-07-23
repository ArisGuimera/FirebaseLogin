package com.aristidevs.nuwelogin.ui.login

data class LoginViewState(
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true
)