package com.example.data.mapper

import com.example.data.model.CommentDTO
import com.example.domain.entity.Comment
import com.example.domain.entity.User

fun CommentDTO.toCommentEntity() : Comment{
    return Comment(id, body, User(userDTO.id,userDTO.username,userDTO.fullName))
}