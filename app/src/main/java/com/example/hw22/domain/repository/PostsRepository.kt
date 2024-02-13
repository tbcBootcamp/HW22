package com.example.hw22.domain.repository

import com.example.hw22.data.common.Resource
import com.example.hw22.domain.model.PostDomainModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<Resource<List<PostDomainModel>>>
}