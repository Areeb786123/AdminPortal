package com.areeb.adminportal.ui.splashScreen.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.areeb.adminportal.databinding.FragmentSplashScreenBinding
import com.areeb.adminportal.ui.base.fragment.BaseFragment
import com.areeb.adminportal.ui.splashScreen.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : BaseFragment() {
    private val viewModel: SplashViewModel by viewModels()
    private var _fragmentBinding: FragmentSplashScreenBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!

    companion object {
        private const val SPLASH_DELAY = 2000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        return _fragmentBinding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToScreen()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun navigateToScreen() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(navigate(), SPLASH_DELAY)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun navigate(): Runnable {
        val safeNavigate = Runnable {
            if (viewModel.sharedPreferences.getToken().isNullOrEmpty()) {
                safeNavigate(SplashScreenDirections.actionSplashScreenToSignInFragment2())
            } else {
                safeNavigate(SplashScreenDirections.actionSplashScreenToHomeFragment3())
            }
        }
        return safeNavigate
    }
}
