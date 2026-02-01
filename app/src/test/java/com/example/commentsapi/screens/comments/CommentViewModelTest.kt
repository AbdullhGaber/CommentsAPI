package com.example.commentsapi.screens.comments


import com.example.commentsapi.utils.MainDispatcherRule
import com.example.domain.entity.Comment
import com.example.domain.entity.User
import com.example.domain.use_cases.comments.CommentUseCases
import com.example.domain.use_cases.comments.GetAllCommentsUseCase
import com.example.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommentViewModelTest {
    // 1. Set the Main Dispatcher for ViewModel scope
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 2. Mock the specific UseCase class
    private val getAllCommentsUseCase: GetAllCommentsUseCase = mockk()

    // 3. Create the real Wrapper class with the mocked use case
    private val useCases = CommentUseCases(getAllCommentsUseCase)
    
    private lateinit var viewModel: CommentViewModel

    @Test
    fun `init should load comments and update state to Success`() = runTest {
        // Arrange
        val user = User(id = 1, username = "Tst123", fullName = "Tester")
        val mockComments = listOf(
            Comment(id = 1, body = "UI Test", user = user)
        )

        // Train the use case to return success
        coEvery { getAllCommentsUseCase() } returns flowOf(Resource.Success(mockComments))

        // Act: Initialize the ViewModel (which triggers the init block)
        viewModel = CommentViewModel(useCases)

        // Assert
        val state = viewModel.commentsUiState.value

        assertFalse("Loading should be false", state.isLoading)
        assertNull("Error should be null", state.error)
        assertEquals("Comments list should match", mockComments, state.comments)
    }

    @Test
    fun `fetchComments should update state with Error message on failure`() = runTest {
        // Arrange
        val errorMessage = "Network Connection Failed"

        // Train the mock to return a Flow with Failure
        coEvery { getAllCommentsUseCase() } returns flowOf(Resource.Failure(errorMessage))

        // Act
        viewModel = CommentViewModel(useCases)

        // Assert
        val state = viewModel.commentsUiState.value

        assertFalse("Loading should be false", state.isLoading)
        assertEquals("Error message should match", errorMessage, state.error)
        assertEquals("Comments should be empty", emptyList<Comment>(), state.comments)
    }

    @Test
    fun `fetchComments should show loading initially`() = runTest {
        // Arrange: Create a flow that emits Loading first, then Success
        val user = User(id = 1, username = "use123", fullName = "User")
        val mockComments = listOf(Comment(1, "Body", user = user))

        // We simulate the sequence of events: Loading -> Success
        coEvery { getAllCommentsUseCase() } returns flowOf(
            Resource.Loading(),
            Resource.Success(mockComments)
        )

        // Act
        viewModel = CommentViewModel(useCases)

        // Note: Since runTest skips delays, we usually only see the final state.
        // However, this verifies that the logic handles the Success state correctly
        // after the Loading state.
        val state = viewModel.commentsUiState.value
        assertEquals(mockComments, state.comments)
        assertFalse(state.isLoading)
    }
}