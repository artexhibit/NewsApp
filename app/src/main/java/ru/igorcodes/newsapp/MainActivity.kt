package ru.igorcodes.newsapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.igorcodes.newsapp.ui.theme.NewsAppTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        showSplashScreenAfter()

        setContent {
            NewsAppTheme {
                // Your Compose content goes here
            }
        }
    }

    private fun showSplashScreenAfter(seconds: Long = 2000) {
        val splashScreen = installSplashScreen()
        var isSplashScreenVisible = true

        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }

        lifecycleScope.launch {
            delay(seconds)
            isSplashScreenVisible = false
        }
    }
}
