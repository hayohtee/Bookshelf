package dev.hayohtee.bookshelf.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    @SerialName("volumeInfo")
    val info: BookInfo
)

@Serializable
data class BookInfo(
    val title: String,
    val description: String,
    val authors: List<String>,
    val publishedDate: String,
    val imageLinks: ImageLink
)

@Serializable
data class ImageLink(
    val thumbnail: String
)
