package com.example.commentsapi.navigation

sealed class Route(val name : String) {
    object CommentsScreen : Route("commentsScreen")
    object CommentsDetailsScreen : Route("commentsDetailsScreen")
}