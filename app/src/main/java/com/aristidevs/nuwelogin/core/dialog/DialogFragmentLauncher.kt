package com.aristidevs.nuwelogin.core.dialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.aristidevs.nuwelogin.core.delegate.weak
import javax.inject.Inject

/**
 * This class exposes a safe way to show dialog fragments.
 *
 * The default behavior for [androidx.fragment.app.DialogFragment] when calling the
 * [androidx.fragment.app.DialogFragment.show] method is to call
 * [androidx.fragment.app.FragmentTransaction.commit]. This method disallows transactions if the
 * state wasn't stored and so it crashes the app.
 *
 * This class checks if the activity is in the right state, that is, it already stored state
 * information. If it isn't in the right state, it subscribes to changes on the activity lifecycle
 * and fires the last dialog.
 *
 * see https://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
 */
class DialogFragmentLauncher @Inject constructor() : LifecycleObserver {

    private var activity: FragmentActivity? by weak()
    private var dialogFragment: DialogFragment? by weak()

    fun show(dialogFragment: DialogFragment, activity: FragmentActivity) {
        if (activity.lifecycle.currentState.isAtLeast(RESUMED)) {
            dialogFragment.show(activity.supportFragmentManager, null)
        } else {
            this.activity = activity
            this.dialogFragment = dialogFragment
            activity.lifecycle.addObserver(this@DialogFragmentLauncher)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onActivityResumed() {
        val activity = activity ?: return
        val dialogFragment = dialogFragment ?: return

        dialogFragment.show(activity.supportFragmentManager, null)
        activity.lifecycle.removeObserver(this)
    }
}

