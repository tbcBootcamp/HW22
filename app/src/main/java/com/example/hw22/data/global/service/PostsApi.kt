package com.example.hw22.data.global.service

import com.example.hw22.data.global.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {
    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>
}