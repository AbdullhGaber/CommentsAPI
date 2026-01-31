package com.example.domain.use_cases.comments

import com.example.domain.entity.Comment
import com.example.domain.repositories.comment.CommentRepository

class GetAllCommentsUseCase(
    private val commentRepository: CommentRepository
){
    operator fun invoke(
        onSuccess : (List<Comment>) -> Unit,
        onFailure : (Throwable) -> Unit
    ){
        commentRepository.getAllComments(onSuccess, onFailure)
    }
}