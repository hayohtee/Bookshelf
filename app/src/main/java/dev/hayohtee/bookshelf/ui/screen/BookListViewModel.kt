package dev.hayohtee.bookshelf.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.hayohtee.bookshelf.BookshelfApplication
import dev.hayohtee.bookshelf.data.BookshelfRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookListViewModel(private val repository: BookshelfRepository): ViewModel() {
    var uiState: BookListUiState by mutableStateOf(BookListUiState.Loading)
        private set

    init {
        fetchBooks("android")
    }

    private fun fetchBooks(title: String) {
        viewModelScope.launch {
            uiState = try {
                val result = repository.searchBooks(title)
                Log.d("BookList", result.totalBooks.toString())
                BookListUiState.Success(result.books)
            } catch (e: IOException) {
                BookListUiState.Error
            } catch (e: HttpException) {
                BookListUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val repository = application.container.repository
                BookListViewModel(repository)
            }
        }
    }
}