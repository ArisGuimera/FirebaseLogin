package com.aristidevs.nuwelogin.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aristidevs.nuwelogin.core.Event
import com.aristidevs.nuwelogin.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }

    private val _navigateToDetails = MutableLiveData<Event<Boolean>>()
    val navigateToDetails: LiveData<Event<Boolean>>
        get() = _navigateToDetails

    private val _navigateToForgotPassword = MutableLiveData<Event<Boolean>>()
    val navigateToForgotPassword: LiveData<Event<Boolean>>
        get() = _navigateToForgotPassword

    private val _navigateToSignIn = MutableLiveData<Event<Boolean>>()
    val navigateToSignIn: LiveData<Event<Boolean>>
        get() = _navigateToSignIn

    private val _navigateToVerifyAccount = MutableLiveData<Event<Boolean>>()
    val navigateToVerifyAccount: LiveData<Event<Boolean>>
        get() = _navigateToVerifyAccount


    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState>
        get() = _viewState

    val loginButtonStatus = MutableLiveData<Boolean>()

    fun onLoginSelected(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = LoginViewState(loading = true)
            val loginResult = loginUseCase(email, password)
            if (loginResult.success) {
                if(loginResult.verified){
                    _navigateToVerifyAccount.value = Event(true)
                }else{
                _navigateToDetails.value = Event(true)
                }
            } else {
                Log.i("Aris", "ARIS 2")
                _viewState.value = LoginViewState(error = true)
            }
        }
    }

    fun onFieldsChanged(email: String, password: String) {
        loginButtonStatus.postValue(isValidEmail(email) && isValidPassword(password))
    }

    fun onForgotPasswordSelected() {
        _navigateToForgotPassword.value = Event(true)
    }

    fun onSignInSelected() {
        _navigateToSignIn.value = Event(true)
    }

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length >= MIN_PASSWORD_LENGTH


}
