package com.example.data.repositories

import com.example.data.mapper.toCommentEntity
import com.example.data.web_service.CommentApiService
import com.example.domain.entity.Comment
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentApiService: CommentApiService
) {
    fun getAllComments() : Flow<Resource<List<Comment>>> = flow {
        emit(Resource.Loading())
        try {
            val response = commentApiService.getComments()
            val domainComments = response.map { it.toCommentEntity() }
            emit(Resource.Success(domainComments))
        } catch (e: Exception) {
            emit(Resource.Failure(e.message ?: "Unknown Error"))
        }
    }
}