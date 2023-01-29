package com.areeb.adminportal.data.repository

import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.data.models.auth.Login.LoginResponseDto
import com.areeb.adminportal.data.network.remote.RemoteOperation
import com.areeb.adminportal.data.network.remote.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(private val remoteOperation: RemoteOperation) {

    fun getData(loginDto: Login): Flow<Resource<LoginResponseDto>> {
        return flow {
            val loginResponse = remoteOperation.login(loginDto)
            emit(loginResponse)
        }.flowOn(Dispatchers.IO)
    }
}
