package com.example.hw22.di

import com.example.hw22.data.common.HandleResponse
import com.example.hw22.data.global.service.PostsApi
import com.example.hw22.data.global.service.StoriesApi
import com.example.hw22.data.repository.PostsRepositoryImpl
import com.example.hw22.data.repository.StoriesRepositoryImpl
import com.example.hw22.domain.repository.PostsRepository
import com.example.hw22.domain.repository.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        service: PostsApi,
        handler: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(service, handler)
    }

    @Provides
    @Singleton
    fun provideStoriesRepository(
        service: StoriesApi,
        handler: HandleResponse
    ): StoriesRepository {
        return StoriesRepositoryImpl(service, handler)
    }

}

