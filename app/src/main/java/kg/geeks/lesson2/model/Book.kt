package kg.geeks.lesson2.model

data class Book(
    val id: Int,
    val title: String,
    val genre: Genre,
    val author: String,
    val image: String,
    val description: String
)