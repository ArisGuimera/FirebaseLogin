package com.aristidevs.nuwelogin.core.ex

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.dismissKeyboard(completed: () -> Unit = {}) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val wasOpened = inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    if (!wasOpened) completed()
}