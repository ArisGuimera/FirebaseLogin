package com.aristidevs.nuwelogin.data.network

import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun login(email:String, password:String) = runCatching{
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.isSuccess
}