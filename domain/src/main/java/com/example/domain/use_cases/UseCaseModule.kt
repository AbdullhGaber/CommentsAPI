package com.example.domain.use_cases

import com.example.domain.repositories.comment.CommentRepository
import com.example.domain.use_cases.comments.CommentUseCases
import com.example.domain.use_cases.comments.GetAllCommentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun getCommentUseCases(commentRepository: CommentRepository) : CommentUseCases {
        return CommentUseCases(GetAllCommentsUseCase(commentRepository))
    }
}