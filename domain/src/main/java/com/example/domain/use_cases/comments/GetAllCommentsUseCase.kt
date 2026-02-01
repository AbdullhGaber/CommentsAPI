package com.example.domain.use_cases.comments

import com.example.domain.entity.Comment
import com.example.domain.repositories.comment.CommentRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllCommentsUseCase(
    private val commentRepository: CommentRepository
){
    operator fun invoke() : Flow<Resource<List<Comment>>>{
        return commentRepository.getAllComments()
    }
}