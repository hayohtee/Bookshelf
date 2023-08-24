package dev.hayohtee.bookshelf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val info: BookInfo
)

@Serializable
data class BookInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val authors: List<String>,
    val publishedDate: String
)
