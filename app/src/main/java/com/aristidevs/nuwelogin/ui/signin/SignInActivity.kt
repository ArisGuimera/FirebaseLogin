package com.aristidevs.nuwelogin.ui.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.nuwelogin.R
import com.aristidevs.nuwelogin.core.ex.dismissKeyboard
import com.aristidevs.nuwelogin.core.ex.loseFocusAfterAction
import com.aristidevs.nuwelogin.core.ex.span
import com.aristidevs.nuwelogin.databinding.ActivitySignInBinding
import com.aristidevs.nuwelogin.ui.login.LoginActivity
import com.aristidevs.nuwelogin.ui.verification.VerificationActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent =
            Intent(context, SignInActivity::class.java)
    }

    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initObservers()
        binding.viewBottom.tvFooter.text = span(
            getString(R.string.signin_footer_unselected),
            getString(R.string.signin_footer_selected)
        )
    }

    private fun initListeners() {

        binding.etEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etEmail.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }

        binding.etNickname.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etNickname.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }

        binding.etRealName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etRealName.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }

        binding.etPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }

        binding.etRepeatPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.etRepeatPassword.setOnFocusChangeListener { _, hasFocus -> onFieldChanged(hasFocus) }

        binding.viewBottom.tvFooter.setOnClickListener { signInViewModel.onLoginSelected() }

        with(binding) {
            btnCreateAccount.setOnClickListener {
                it.dismissKeyboard()
                signInViewModel.onSignInSelected(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
    }

    private fun initObservers() {
        signInViewModel.navigateToVerifyEmail.observe(this, {
            it.getContentIfNotHandled()?.let {
                goToVerifyEmail()
            }
        })

        signInViewModel.navigateToLogin.observe(this, {
            it.getContentIfNotHandled()?.let {
                goToLogin()
            }
        })
    }

    private fun onFieldChanged(hasFocus: Boolean) {
        if (!hasFocus) {
//            signInViewModel.onFieldsChanged(
//                email = binding.etEmail.text.toString(),
//                password = binding.etPassword.text.toString()
//            )
        }
    }

    private fun goToVerifyEmail() {
        startActivity(VerificationActivity.create(this))
    }

    private fun goToLogin() {
        startActivity(LoginActivity.create(this))
    }
}