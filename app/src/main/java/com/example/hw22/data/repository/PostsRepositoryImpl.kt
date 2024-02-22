package com.example.hw22.data.repository

import com.example.hw22.data.common.HandleResponse
import com.example.hw22.data.common.Resource
import com.example.hw22.data.global.mapper.base.mapListToDomain
import com.example.hw22.data.global.mapper.base.mapToDomain
import com.example.hw22.data.global.mapper.toDomain
import com.example.hw22.data.global.service.PostsApi
import com.example.hw22.domain.model.PostDomainModel
import com.example.hw22.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val service: PostsApi,
    private val handler: HandleResponse
) : PostsRepository {
    override suspend fun getPosts(): Flow<Resource<List<PostDomainModel>>> {
        return handler.safeApiCall { service.getPosts() }.mapListToDomain { it.toDomain() }
    }

    override suspend fun getPostDetails(id: Int): Flow<Resource<PostDomainModel>> {
        return handler.safeApiCall {service.getPostDetails(id)}.mapToDomain {
            it.toDomain()
        }
    }
}