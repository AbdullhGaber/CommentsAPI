package com.example.commentsapi.screens.comments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.commentsapi.screens.comments.components.CommentItem
import com.example.domain.utils.Resource
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    modifier: Modifier = Modifier,
    viewModel: CommentViewModel,
    onCommentClick : (index : Int) -> Unit
) {
    val commentsUiState = viewModel.commentsUiState

    if(commentsUiState.value.isLoading){
        Box(
            modifier.fillMaxSize()
        ){
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }else{
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Comments Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
                )
            },
            containerColor = Color(0xFFF7F7F7)
        ) { padding ->
            if(commentsUiState.value.error == null){
                val comments = commentsUiState.value.comments
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(comments) { index, comment ->
                        CommentItem(
                            comment = comment,
                            onCommentClick = {
                                onCommentClick(index)
                            }
                        )
                    }
                }
            }else{
                Box(
                    modifier.fillMaxSize()
                ){
                    Text(
                        text = "Something went wrong : " + commentsUiState.value.error,
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewCommentsScreen() {

}