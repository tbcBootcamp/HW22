package com.example.hw22.domain.usecase

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.StoryDomainModel
import com.example.hw22.domain.repository.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(private val repository: StoriesRepository) {

    suspend operator fun invoke(): Flow<Resource<List<StoryDomainModel>>> {
        return repository.getStories()
    }
}