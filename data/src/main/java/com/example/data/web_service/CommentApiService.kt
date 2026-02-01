package com.example.data.web_service

import com.example.data.model.CommentDTO
import retrofit2.http.GET

interface CommentApiService {
    @GET("comments")
    suspend fun getComments() : List<CommentDTO>
}