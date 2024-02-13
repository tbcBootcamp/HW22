package com.example.hw22.data.repository

import com.example.hw22.data.common.HandleResponse
import com.example.hw22.data.common.Resource
import com.example.hw22.data.global.mapper.base.mapListToDomain
import com.example.hw22.data.global.mapper.toDomain
import com.example.hw22.data.global.service.StoriesApi
import com.example.hw22.domain.model.StoryDomainModel
import com.example.hw22.domain.repository.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val service: StoriesApi,
    private val handler: HandleResponse
) : StoriesRepository {
    override suspend fun getStories(): Flow<Resource<List<StoryDomainModel>>> {
        return handler.safeApiCall { service.getStories() }.mapListToDomain { it.toDomain() }
    }
}