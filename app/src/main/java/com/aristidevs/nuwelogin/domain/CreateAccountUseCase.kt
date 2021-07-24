package com.aristidevs.nuwelogin.domain

import com.aristidevs.nuwelogin.data.network.AuthenticationService
import com.aristidevs.nuwelogin.ui.signin.model.UserSignIn
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(userSignIn: UserSignIn):Boolean{
        authenticationService.createAccount(userSignIn.email, userSignIn.password)
        return true
    }
}