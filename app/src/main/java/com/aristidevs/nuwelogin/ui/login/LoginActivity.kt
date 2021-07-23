package com.aristidevs.nuwelogin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aristidevs.nuwelogin.core.ex.loseFocusAfterAction
import com.aristidevs.nuwelogin.core.ex.onTextChanged
import com.aristidevs.nuwelogin.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
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
    }

    private fun initListeners() {
        hookListeners()
        binding.tvForgotPassword.setOnClickListener { loginViewModel.onForgotPasswordSelected() }
        binding.viewBottom.tvSignIn.setOnClickListener { loginViewModel.onSignInSelected() }
        binding.btnLogin.setOnClickListener {
            loginViewModel.onLoginSelected(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

//        binding.etEmail.onTextChanged {
//            loginViewModel.onFieldsChanged(
//                email = it,
//                password = binding.etPassword.text.toString()
//            )
//        }
//
//        binding.etPassword.onTextChanged {
//            loginViewModel.onFieldsChanged(
//                email = binding.etEmail.text.toString(),
//                password = it
//            )
//        }

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

        loginViewModel.navigateToVerifyAccount.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goToVerify()
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
            pbLoading.isVisible = viewState.isLoading
            btnLogin.isEnabled = viewState.isLoginEnabled
            binding.tilEmail.error = if (viewState.isValidEmail) null else "adwda"
            binding.tilPassword.error = if (viewState.isValidPassword) null else "2313123123"
        }
    }

    private fun goToForgotPassword() {

    }

    private fun goToSignIn() {

    }

    private fun goToDetail() {

    }

    private fun goToVerify() {

    }

    fun hookListeners() {
        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }
    }

    fun onFieldChanged(hasFocus:Boolean){
        if (!hasFocus) {
            loginViewModel.onFieldsChanged(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
    }
}