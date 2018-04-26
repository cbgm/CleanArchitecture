package com.example.christian.cleantest.core.dagger.modules

import com.example.christian.cleantest.data.repository.UserRepositoryImpl
import com.example.christian.cleantest.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }
}