package kg.geeks.lesson2.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import kg.geeks.lesson2.model.Book
import kg.geeks.lesson2.model.Genre

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    onGenreSelected: (Genre?) -> Unit,
    onSearch: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf<Genre?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Поле поиска
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearch(it)
            },
            label = { Text("Поиск по названию или автору") },
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.systemBars),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5)
            )
        )

        // Фильтр по жанрам (LazyRow)
        GenreFilterRow(
            genres = Genre.values().toList(),
            selectedGenre = selectedGenre,
            onGenreSelected = { genre ->
                selectedGenre = if (selectedGenre == genre) null else genre
                onGenreSelected(selectedGenre)
            }
        )

        // Список книг
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            items(books) { book ->
                BookListItem(book = book, onClick = { onBookClick(book) })
                Divider()
            }
        }
    }
}

@Composable
fun BookListItem(book: Book, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        Image(
            painter = rememberAsyncImagePainter(book.image),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF2C2C2C)
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2C2C2C)
            )
        }
    }
}

@Composable
fun GenreFilterRow(
    genres: List<Genre>,
    selectedGenre: Genre?,
    onGenreSelected: (Genre) -> Unit
) {
    LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
        items(genres) { genre ->
            val isSelected = genre == selectedGenre
            Text(
                text = genre.name,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onGenreSelected(genre) }
                    .background(
                        color = if (isSelected) Color(0xFF6A4E3C) else Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = if (isSelected) Color(0xFFF5F5F5) else Color(0xFF2C2C2C)
            )
        }
    }
}
