package com.example.domain.use_cases

import com.example.domain.entity.Comment
import com.example.domain.entity.User
import com.example.domain.repositories.comment.CommentRepository
import com.example.domain.use_cases.comments.GetAllCommentsUseCase
import com.example.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllCommentsUseCaseTest {

    // 1. Mock the Repository Interface
    private val repository: CommentRepository = mockk()
    
    // 2. Initialize the UseCase with the mocked repository
    private val useCase = GetAllCommentsUseCase(repository)

    @Test
    fun `invoke should return list of comments from repository`() = runTest {
        // Arrange: Create a fake list of domain comments
        val user1 = User(id = 1, "User1", "Full User Name 1")
        val user2 = User(id = 2, "User2", "Full User Name 2")
        val mockComments = listOf(
            Comment(id = 1, body = "Test 1", user = user1),
            Comment(id = 2, body = "Test 2", user = user2)
        )
        
        // "Train" the mock: When repo.getAllComments() is called, return our fake list
        coEvery { repository.getAllComments() } returns flowOf(Resource.Success(mockComments))

        // Act: Call the use case
        val resultFlow = useCase()

        // Assert: Collect the flow and check the data
        resultFlow.collect { resource ->
            assert(resource is Resource.Success)
            assertEquals(mockComments, (resource as Resource.Success).data)
        }
        
        // Verify the repository was actually called exactly once
        coVerify(exactly = 1) { repository.getAllComments() }
    }
}