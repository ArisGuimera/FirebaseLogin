package com.aristidevs.nuwelogin.login.ui

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private companion object{
        const val MIN_PASSWORD_LENGTH = 8
    }

    val loginButtonStatus = MutableLiveData<Boolean>()

    fun onLoginSelected() {

    }

    fun onFieldsChanged(email: String, password: String) {
        loginButtonStatus.postValue(isValidEmail(email) && isValidPassword(password))
    }

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length >= MIN_PASSWORD_LENGTH



}
