package com.aristidevs.nuwelogin.core.ex

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.aristidevs.nuwelogin.core.dialog.DialogFragmentLauncher

/**
 * Just an extension to avoid having to create dialogs, storing them on a variable and then calling
 * launcher.show. With this extension we can create the dialog and show it in a fluent manner.
 */
fun DialogFragment.show(launcher: DialogFragmentLauncher, activity: FragmentActivity) {
    launcher.show(this, activity)
}
