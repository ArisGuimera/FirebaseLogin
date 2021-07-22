package com.aristidevs.nuwelogin.domain

import com.aristidevs.nuwelogin.data.network.AuthenticationService
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email: String, password: String)=
        authenticationService.createAccount(email, password)
}