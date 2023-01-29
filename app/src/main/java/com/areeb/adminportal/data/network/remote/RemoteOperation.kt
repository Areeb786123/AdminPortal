package com.areeb.adminportal.data.network.remote

import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.data.models.auth.Login.LoginResponseDto
import com.areeb.adminportal.data.network.remote.auth.AuthApi
import javax.inject.Inject

class RemoteOperation @Inject constructor(private val authApi: AuthApi) :
    IRemoteOperation, SafeApiCall {
    override suspend fun login(login: Login): Resource<LoginResponseDto> {
        return safeApiCall { authApi.login(login) }
    }
}
