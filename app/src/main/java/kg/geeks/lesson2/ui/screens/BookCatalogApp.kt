import androidx.compose.runtime.*
import data.fakeBooks
import kg.geeks.lesson2.model.Genre
import kg.geeks.lesson2.navigation.Screen
import kg.geeks.lesson2.ui.screens.BookListScreen
import kg.geeks.lesson2.ui.screens.DetailBook

@Composable
fun BookCatalogApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.BookList) }
    var filteredBooks by remember { mutableStateOf(fakeBooks) }

    fun filterBooks(genre: Genre? = null, query: String = "") {
        filteredBooks = fakeBooks.filter { book ->
            (genre == null || book.genre == genre) &&
                    (query.isEmpty() ||
                            book.title.contains(query, ignoreCase = true) ||
                            book.author.contains(query, ignoreCase = true))
        }
    }

    when (val screen = currentScreen) {
        is Screen.BookList -> {
            BookListScreen(
                books = filteredBooks,
                onBookClick = { book ->
                    currentScreen = Screen.DetailBook(book)
                },
                onGenreSelected = { genre ->
                    filterBooks(genre = genre)
                },
                onSearch = { query ->
                    filterBooks(query = query)
                }
            )
        }
        is Screen.DetailBook -> {
            DetailBook(
                book = screen.book,
                onBack = {
                    currentScreen = Screen.BookList
                }
            )
        }
    }
}