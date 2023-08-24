package dev.hayohtee.bookshelf.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val repository: BookshelfRepository
}
class BookshelfContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val repository: BookshelfRepository by lazy {
        BookshelfRemoteRepository(apiService)
    }
}