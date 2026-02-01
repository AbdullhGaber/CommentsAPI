import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.entity.Comment
import com.example.domain.entity.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsDetailsScreen(
    comment: Comment,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comment", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        Card(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = comment.user.fullName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = comment.body, fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

                DetailRow(label = "ID", value = "${comment.id}")

                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

                DetailRow(label = "username", value = comment.user.username)
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 16.sp)
        Text(text = value, color = Color.Black, fontSize = 16.sp)
    }
}

@Preview
@Composable
private fun PreviewCommentsDetailsScreen() {
    CommentsDetailsScreen(
        comment = Comment(
            id = 1,
            body = "some comment",
            user = User(
                1,
                "mustafaa",
                "Mustafa"
            )
        ),
        onBackClick = {}
    )
}