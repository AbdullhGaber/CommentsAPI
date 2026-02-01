package com.example.commentsapi.screens.comments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.comments.CommentUseCases
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentsUseCases : CommentUseCases,
) : ViewModel() {
    private val _commentsUiState = mutableStateOf(CommentUIState())
    val commentsUiState : State<CommentUIState> = _commentsUiState

    init {
        fetchComments()
    }

    fun fetchComments() {
        viewModelScope.launch {
            commentsUseCases.getAllCommentsUseCase().collect { result ->
                println("Debug: Received result $result") // Check your Logcat!
                _commentsUiState.value = when(result) {
                    is Resource.Loading -> _commentsUiState.value.copy(isLoading = true)
                    is Resource.Success -> _commentsUiState.value.copy(
                        isLoading = false,
                        comments = result.data ?: emptyList(),
                        error = null
                    )
                    // Use the base class or check if it's named 'Error' in your project
                    is Resource.Failure -> _commentsUiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                    else -> _commentsUiState.value.copy(isLoading = false)
                }
            }
        }
    }
}