package com.areeb.adminportal.data.network.remote

import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.data.models.auth.Login.LoginResponseDto

interface IRemoteOperation {

    suspend fun login(login: Login): Resource<LoginResponseDto>
}
