package com.example.hw22.domain.usecase

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.PostDomainModel
import com.example.hw22.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject




class GetPostDetailsUseCase @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke(id:Int): Flow<Resource<PostDomainModel>> {
        return repository.getPostDetails(id)
    }
}