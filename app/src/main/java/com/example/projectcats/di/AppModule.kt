package com.example.projectcats.di

import com.example.projectcats.data.resource.RestDataSource
import com.example.projectcats.domain.repository.CatRepository
import com.example.projectcats.domain.repository.CatRepositoryImpl
import com.example.projectcats.domain.useCase.CatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerCatRepository(restDataSource: RestDataSource): CatRepository {
        return CatRepositoryImpl(restDataSource)
    }

    @Singleton
    @Provides
    fun providerCatUseCase(catRepository: CatRepository): CatsUseCase {
        return CatsUseCase(catRepository = catRepository)
    }
}