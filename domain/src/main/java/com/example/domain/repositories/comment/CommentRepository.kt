package com.example.domain.repositories.comment

import com.example.domain.entity.Comment

interface CommentRepository {
    fun getAllComments(
        onSuccess : (List<Comment>) -> Unit,
        onFailure : (Throwable) -> Unit
    )
}