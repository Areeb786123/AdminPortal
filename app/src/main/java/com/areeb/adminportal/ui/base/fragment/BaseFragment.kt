package com.areeb.adminportal.ui.base.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    companion object {
        private const val TAG = "tag"
    }

    protected fun safeNavigate(navDirections: NavDirections) {
        try {
            findNavController().currentDestination?.getAction(navDirections.actionId)?.run {
                findNavController().navigate(navDirections)
            }
        } catch (exception: IllegalStateException) {
            Log.e(TAG, exception.toString())
        }
    }

    protected fun safeNavigate(
        navDirection: NavDirections,
        bundle: Bundle? = null,
        resId: Int
    ) {
        try {
            findNavController().currentDestination?.getAction(navDirection.actionId)?.run {
                findNavController().navigate(resId, bundle)
            }
        } catch (exception: IllegalStateException) {
            Log.e(TAG, exception.toString())
        }
    }
}
