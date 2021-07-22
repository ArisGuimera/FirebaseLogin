package com.aristidevs.nuwelogin.data.network

import android.content.Intent
import android.content.Intent.getIntent
import androidx.core.content.ContextCompat.startActivity
import com.aristidevs.nuwelogin.data.response.LoginResponse
import com.aristidevs.nuwelogin.ui.login.LoginActivity
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun login(email: String, password: String): LoginResponse {
        val auth = firebase.auth.signInWithEmailAndPassword(email, password).await()
        return LoginResponse(success = auth != null, verified = auth.user?.isEmailVerified ?: false)
    }

    //Peta con mal mail
    suspend fun createAccount(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun sendVerificationEmail() = runCatching {
        firebase.auth.currentUser?.sendEmailVerification()?.await() ?: false
    }.isSuccess

    fun verifyEmail(): Flow<Boolean> = callbackFlow {
        firebase.auth.currentUser?.isEmailVerified
    }


}