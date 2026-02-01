package com.example.data.repositories

import com.example.data.mapper.toCommentEntity
import com.example.data.web_service.CommentApiService
import com.example.domain.entity.Comment
import com.example.domain.repositories.comment.CommentRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentApiService: CommentApiService
) : CommentRepository {
    override fun getAllComments(): Flow<Resource<List<Comment>>> = flow {
        emit(Resource.Loading())
        println("Debug: Flow Started")
        try {
            println("Debug: Making API Call...")
            // Move the execution to the IO thread
            val response = withContext(Dispatchers.IO) {
                commentApiService.getComments()
            }
            println("Debug: API Call Finished. Size: ${response.size}")
            val domainComments = response.map { it.toCommentEntity() }
            emit(Resource.Success(domainComments))
        } catch (e: Exception) {
            println("Debug: API Call Failed: ${e.message}")
            emit(Resource.Failure(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO) // Ensures the entire flow logic runs on IO
}