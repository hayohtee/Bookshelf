package dev.hayohtee.bookshelf.ui.screen

import dev.hayohtee.bookshelf.data.model.Book

sealed interface BookListUiState {
    data object Error: BookListUiState
    data object Loading: BookListUiState
    data class Success(val books: List<Book>): BookListUiState
}