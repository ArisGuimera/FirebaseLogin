package com.aristidevs.nuwelogin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aristidevs.nuwelogin.core.ex.onTextChanged
import com.aristidevs.nuwelogin.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collect

class LoginActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, LoginActivity::class.java)

    }

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
        binding.tvForgotPassword.setOnClickListener { loginViewModel.onForgotPasswordSelected() }
        binding.viewBottom.tvSignIn.setOnClickListener { loginViewModel.onSignInSelected() }
        binding.btnLogin.setOnClickListener {
            loginViewModel.onLoginSelected(
                binding.etUser.text.toString(),
                binding.etPassword.text.toString()
            )
        }

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

        loginViewModel.navigateToDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToDetail()
            }
        })

        loginViewModel.navigateToSignIn.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToSignIn()
            }
        })

        loginViewModel.navigateToForgotPassword.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToForgotPassword()
            }
        })

        lifecycleScope.launchWhenStarted {
            loginViewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun updateUI(viewState: LoginViewState) {
        with(binding) {
            pbLoading.isVisible = viewState.loading
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

    private fun goToDetail() {

    }
}