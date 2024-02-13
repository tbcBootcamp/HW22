package com.example.hw22.domain.repository

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.StoryDomainModel
import kotlinx.coroutines.flow.Flow


interface StoriesRepository {
    suspend fun getStories(): Flow<Resource<List<StoryDomainModel>>>
}