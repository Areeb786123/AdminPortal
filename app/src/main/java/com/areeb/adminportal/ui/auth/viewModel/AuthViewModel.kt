package com.areeb.adminportal.ui.auth.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.data.models.auth.Login.LoginResponseDto
import com.areeb.adminportal.data.network.remote.Resource
import com.areeb.adminportal.data.repository.AuthRepository
import com.areeb.adminportal.ui.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    companion object {
        private const val TAG = "authViewModel"
    }

    private val _authToken = MutableLiveData<String?>()
    val authToken: LiveData<String?>
        get() = _authToken

    @RequiresApi(Build.VERSION_CODES.M)
    fun login(login: Login) {
        viewModelScope.launch {
            repository.getData(login)
                .catch {
                    Log.e(TAG, TAG)
                }.collect {
                    setAuthToken(it)
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAuthToken(authToken: Resource<LoginResponseDto>) {
        viewModelScope.launch {
            if (authToken is Resource.Success) {
                authToken.data.let {
                    _authToken.value = it.auth_token
                    sharedPreferences.saveToken(it.auth_token)
                }
            }
        }
    }
}
