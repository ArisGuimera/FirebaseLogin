package com.aristidevs.nuwelogin.login.ui

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aristidevs.nuwelogin.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }

    val loginButtonStatus = MutableLiveData<Boolean>()

    fun onLoginSelected(email: String, password: String) {
        viewModelScope.launch {
            val successResult: Boolean = loginUseCase(email, password)
            if (successResult) {

            } else {

            }
        }
    }

    fun onFieldsChanged(email: String, password: String) {
        loginButtonStatus.postValue(isValidEmail(email) && isValidPassword(password))
    }

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length >= MIN_PASSWORD_LENGTH


}
