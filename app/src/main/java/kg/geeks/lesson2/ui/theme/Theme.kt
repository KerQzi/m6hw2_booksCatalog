package kg.geeks.lesson2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val JustParchment8ColorPalette = lightColorScheme(
    primary = Color(0xFFB8A18D), // Теплый бежевый, напоминающий цвет пергамента
    secondary = Color(0xFF6A4E3C), // Глубокий коричневый, ассоциирующийся с чернилами
    background = Color(0xFFF5F5F5), // Светлый бежевый, напоминающий цвет бумаги
    surface = Color(0xFF2C2C2C), // Темный угольный, придающий глубину
    onPrimary = Color.Black, // Черный текст на основном цвете
    onSecondary = Color.White, // Белый текст на акцентном цвете
    onBackground = Color.Black, // Черный текст на светлом фоне
    onSurface = Color.White // Белый текст на темной поверхности
)






@Composable
fun Lesson2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = JustParchment8ColorPalette,
        typography = Typography, // настройте типографику по желанию
        content = content
    )
}

