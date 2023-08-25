package dev.hayohtee.bookshelf.data

import dev.hayohtee.bookshelf.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("/books/v1/volumes")
    suspend fun searchBooks(@Query("q") title: String): BookResponse
}