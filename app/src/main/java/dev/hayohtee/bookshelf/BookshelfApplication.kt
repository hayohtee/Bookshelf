package dev.hayohtee.bookshelf

import android.app.Application
import dev.hayohtee.bookshelf.data.BookshelfContainer

class BookshelfApplication: Application() {
    private lateinit var container: BookshelfContainer
    override fun onCreate() {
        super.onCreate()
        container = BookshelfContainer()
    }
}