package dev.hayohtee.bookshelf.data

import dev.hayohtee.bookshelf.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("/books/v1/volumes?q={title}")
    suspend fun searchBooks(@Path("title") title: String): BookResponse
}