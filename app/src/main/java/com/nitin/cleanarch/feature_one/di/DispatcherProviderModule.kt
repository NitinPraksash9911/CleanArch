package com.nitin.cleanarch.feature_one.di

import com.nitin.cleanarch.feature_one.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherProviderModule {

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default

    }
}