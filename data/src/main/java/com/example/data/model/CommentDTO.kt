package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CommentDTO(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val body: String,
    val email: String,
)