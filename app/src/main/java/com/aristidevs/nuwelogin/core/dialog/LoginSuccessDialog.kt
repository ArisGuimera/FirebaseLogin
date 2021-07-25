package com.aristidevs.nuwelogin.core.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.aristidevs.nuwelogin.databinding.DialogErrorBinding
import com.aristidevs.nuwelogin.databinding.DialogLoginSuccessBinding

class LoginSuccessDialog : DialogFragment() {

    companion object {
        fun create(): LoginSuccessDialog = LoginSuccessDialog()
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogLoginSuccessBinding.inflate(requireActivity().layoutInflater)
        binding.btnPositive.setOnClickListener { dismiss() }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(true)
            .create()
    }
}