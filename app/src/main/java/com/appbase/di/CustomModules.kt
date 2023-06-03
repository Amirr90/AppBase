package com.appbase.di

import com.appbase.demoTest.Authentication
import com.appbase.demoTest.FirebaseLogin
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CustomModules {

    @Binds
    @Singleton
    abstract fun bindsAuth(auth: FirebaseLogin): Authentication
}