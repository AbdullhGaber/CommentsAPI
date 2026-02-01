package com.example.commentsapi.screens.comments

import com.example.domain.entity.Comment

data class CommentUIState(
    val comments : List<Comment> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null
)
