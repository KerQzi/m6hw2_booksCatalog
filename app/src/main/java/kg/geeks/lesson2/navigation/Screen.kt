package kg.geeks.lesson2.navigation

import kg.geeks.lesson2.model.Book

sealed class Screen {
    object BookList : Screen()
    data class DetailBook(val book: Book) : Screen()
}