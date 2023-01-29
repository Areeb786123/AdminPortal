package com.areeb.adminportal.di

import com.areeb.adminportal.data.network.remote.RemoteDataSource
import com.areeb.adminportal.data.network.remote.auth.AuthApi
import com.areeb.adminportal.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesAuthApi(
        remoteDataSource: RemoteDataSource
    ): AuthApi {
        return remoteDataSource.buildApi(
            AuthApi::class.java,
            Constants.AuthDetails.BASE_URL
        )
    }
}
