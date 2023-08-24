package dev.hayohtee.bookshelf.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    @SerialName("totalItems")
    val totalBooks: Int,
    @SerialName("items")
    val books: List<Book>
)
