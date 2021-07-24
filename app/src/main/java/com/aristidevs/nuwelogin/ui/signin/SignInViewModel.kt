package com.aristidevs.nuwelogin.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aristidevs.nuwelogin.core.Event
import com.aristidevs.nuwelogin.domain.CreateAccountUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val createAccountUseCase: CreateAccountUseCase) :
    ViewModel() {

    private val _navigateToLogin = MutableLiveData<Event<Boolean>>()
    val navigateToLogin: LiveData<Event<Boolean>>
        get() = _navigateToLogin

    private val _navigateToVerifyEmail = MutableLiveData<Event<Boolean>>()
    val navigateToVerifyEmail: LiveData<Event<Boolean>>
        get() = _navigateToVerifyEmail

    fun onSignInSelected(email: String, password: String) {
        viewModelScope.launch {
            val a: AuthResult? = createAccountUseCase(email, password)

            if(a != null){
                _navigateToVerifyEmail.value = Event(true)
            }

        }

    }

    fun onLoginSelected() {
        _navigateToLogin.value = Event(true)
    }

}