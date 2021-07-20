package com.aristidevs.nuwelogin.login.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.nuwelogin.core.ex.onTextChanged
import com.aristidevs.nuwelogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.tvForgotPassword.setOnClickListener { goToForgotPassword() }
        binding.tvSignIn.setOnClickListener { goToSignIn() }
        binding.btnLogin.setOnClickListener { loginViewModel.onLoginSelected() }

        binding.etUser.onTextChanged {
            loginViewModel.onFieldsChanged(
                email = it,
                password = binding.etPassword.text.toString()
            )
        }
        binding.etPassword.onTextChanged {
            loginViewModel.onFieldsChanged(
                email = binding.etUser.text.toString(),
                password = it
            )
        }
    }

    private fun initObservers() {
        loginViewModel.loginButtonStatus.observe(this, { enabled ->
            binding.btnLogin.isEnabled = enabled
        })
    }

    private fun goToForgotPassword() {

    }

    private fun goToSignIn() {

    }
}