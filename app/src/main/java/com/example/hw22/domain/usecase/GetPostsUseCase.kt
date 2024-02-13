package com.example.hw22.domain.usecase

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.PostDomainModel
import com.example.hw22.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke(): Flow<Resource<List<PostDomainModel>>> {
        return repository.getPosts()
    }
}