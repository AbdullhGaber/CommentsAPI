package com.example.commentsapi.screens.comments
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.commentsapi.screens.comments.components.CommentItem
import com.example.domain.entity.Comment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(modifier : Modifier = Modifier) {
    val dummyComments = List(20) { i ->
        Comment(id = i, title = "Title", body = "Body")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comments Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
            )
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyComments) { comment ->
                CommentItem(comment)
            }
        }
    }
}


@Preview
@Composable
private fun PreviewCommentsScreen() {

}