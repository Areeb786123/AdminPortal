package com.areeb.adminportal.ui.auth.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.databinding.FragmentSignInBinding
import com.areeb.adminportal.ui.auth.viewModel.AuthViewModel
import com.areeb.adminportal.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment(), View.OnClickListener {
    private val viewModel: AuthViewModel by viewModels()
    private var _fragmentBinding: FragmentSignInBinding? = null
    private val fragmentBinding
        get() = _fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return _fragmentBinding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun navigateTheUser() {
        viewModel.authToken.observe(viewLifecycleOwner) {
            if (it == null || viewModel.sharedPreferences.getToken()?.isBlank() == true) {
                Toast.makeText(context, "please login", Toast.LENGTH_SHORT).show()
            } else {
                safeNavigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
            }
        }
    }

    private fun setOnViewClickListener() {
        fragmentBinding.loginButton.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        setOnViewClickListener()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View?) {
        when (view?.id) {
            fragmentBinding.loginButton.id -> {
                sendLoginResponse()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendLoginResponse() {
        if (fragmentBinding.loginTextView.text.isNotEmpty() && fragmentBinding.passWordTextView.text.isNotEmpty()) {
            val loginDto = Login(
                fragmentBinding.userNameTextView.text.toString(),
                fragmentBinding.passWordTextView.text.toString()
            )

            viewModel.login(loginDto)
            navigateTheUser()
        } else {
            Toast.makeText(context, "Some error occur ", Toast.LENGTH_SHORT).show()
        }
    }
}
