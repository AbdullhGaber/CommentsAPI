package com.example.data.utils

import com.example.data.model.CommentDTO
import com.example.data.model.UserDTO
import com.example.domain.entity.Comment
import com.example.domain.entity.User

object MockData {

    // 1. Create the nested UserDTO
    val mockUserDTO = UserDTO(
        id = 99,
        username = "abdullh_dev",
        fullName = "Abdullh Gaber"
    )

    // 2. Create the CommentDTO (using the UserDTO above)
    val mockCommentDTO = CommentDTO(
        id = 1,
        body = "This is a clean architecture test.",
        userDTO = mockUserDTO
    )

    // 3. Create the Expected Domain Comment (What the DTO should look like after mapping)
    // NOTE: Update these fields to match your actual 'Comment' entity class in Domain
    val mockDomainUser = User(
        id = 99,
        username = "abdullh_dev",
        fullName = "Abdullh Gaber"
    )

    val mockDomainComment = Comment(
        id = 1,
        body = "This is a clean architecture test.",
        user = mockDomainUser
    )
}