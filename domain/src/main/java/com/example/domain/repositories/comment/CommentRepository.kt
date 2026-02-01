package com.example.domain.repositories.comment

import com.example.domain.entity.Comment
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getAllComments() : Flow<Resource<List<Comment>>>
}