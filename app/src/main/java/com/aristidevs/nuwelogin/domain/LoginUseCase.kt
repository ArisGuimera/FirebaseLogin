package com.aristidevs.nuwelogin.domain

import com.aristidevs.nuwelogin.data.network.AuthenticationService
import com.aristidevs.nuwelogin.data.response.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)
}