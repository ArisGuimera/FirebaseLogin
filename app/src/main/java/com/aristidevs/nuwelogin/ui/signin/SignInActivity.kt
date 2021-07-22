package com.aristidevs.nuwelogin.ui.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.nuwelogin.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun initListeners() {
        with(binding) {
            btnCreateAccount.setOnClickListener {
                signInViewModel.onSignInSelected(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
        signInViewModel.navigateToVerifyEmail.observe(this, {
            it.getContentIfNotHandled()?.let {
                goToVerifyEmail()
            }
        })
    }

    private fun goToVerifyEmail() {
//        startActivity()
        Toast.makeText(this, "Ddawd", Toast.LENGTH_LONG).show()
    }
}