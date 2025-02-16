package kg.geeks.lesson2.ui.activity

import BookCatalogApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kg.geeks.lesson2.ui.theme.Lesson2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lesson2Theme {
                BookCatalogApp()
            }
        }
    }
}