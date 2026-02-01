package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CommentDTO(
    val id: Int,
    val body: String,
    @SerializedName("user")
    val userDTO: UserDTO
)