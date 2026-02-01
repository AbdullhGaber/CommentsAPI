package com.example.data.mapper

import com.example.data.model.CommentDTO
import com.example.domain.entity.Comment

fun CommentDTO.toCommentEntity() : Comment{
    return Comment(id, title, body)
}