package com.aristidevs.nuwelogin.domain

import com.aristidevs.nuwelogin.data.network.AuthenticationService
import com.aristidevs.nuwelogin.data.network.UserService
import com.aristidevs.nuwelogin.ui.signin.model.UserSignIn
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    suspend operator fun invoke(userSignIn: UserSignIn): Boolean {
        val accountCreated =
            authenticationService.createAccount(userSignIn.email, userSignIn.password) != null
        return if (accountCreated) {
            userService.createUserTable(userSignIn)
        } else {
            false
        }
    }
}