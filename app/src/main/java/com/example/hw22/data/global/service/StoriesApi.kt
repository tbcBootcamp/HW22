package com.example.hw22.data.global.service

import com.example.hw22.data.global.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface StoriesApi {
    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStories(): Response<List<StoryDto>>
}