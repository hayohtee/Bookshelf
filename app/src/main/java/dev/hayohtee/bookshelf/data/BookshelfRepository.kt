package dev.hayohtee.bookshelf.data

import dev.hayohtee.bookshelf.data.model.Book
import dev.hayohtee.bookshelf.data.model.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BookshelfRepository {
    suspend fun searchBooks(title: String): BookResponse
}

class BookshelfRemoteRepository(private val apiService: BookshelfApiService) : BookshelfRepository {
    override suspend fun searchBooks(title: String): BookResponse {
        return withContext(Dispatchers.IO) {
            apiService.searchBooks(title)
        }
    }
}