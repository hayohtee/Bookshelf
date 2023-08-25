package dev.hayohtee.bookshelf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.hayohtee.bookshelf.R
import dev.hayohtee.bookshelf.data.model.Book
import dev.hayohtee.bookshelf.data.model.BookInfo
import dev.hayohtee.bookshelf.ui.theme.BookshelfTheme

@Composable
fun BookListScreen(uiState: BookListUiState, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        when (uiState) {
            is BookListUiState.Error -> ErrorScreen(modifier = Modifier.align(Alignment.Center))
            is BookListUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
            )

            is BookListUiState.Success -> BookGrid(
                books = uiState.books,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}


@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = stringResource(id = R.string.error_message),
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.error
            )
        )
    }
}

@Composable
fun BookCard(bookInfo: BookInfo, modifier: Modifier = Modifier) {
    val url = bookInfo.imageLinks.thumbnail.replace("http", "https")
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = bookInfo.title,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        placeholder = painterResource(id = R.drawable.placeholder)
    )
}

@Composable
fun BookGrid(books: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(items = books, key = { book -> book.id }) { book ->
            BookCard(
                bookInfo = book.info,
                modifier = Modifier.size(200.dp, 300.dp).padding(4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    BookshelfTheme {
        BookListScreen(uiState = BookListUiState.Loading)
    }
}