package com.areeb.adminportal.data.network.remote.auth

import com.areeb.adminportal.data.models.auth.Login.Login
import com.areeb.adminportal.data.models.auth.Login.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/login")
    suspend fun login(@Body login: Login): LoginResponseDto
}
