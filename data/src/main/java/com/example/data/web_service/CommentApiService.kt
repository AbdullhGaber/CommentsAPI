package com.example.data.web_service


import com.example.data.model.CommentResponse
import retrofit2.http.GET

interface CommentApiService {
    @GET("comments")
    suspend fun getComments() : CommentResponse
}