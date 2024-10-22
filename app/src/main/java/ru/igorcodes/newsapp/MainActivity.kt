package ru.igorcodes.newsapp
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.igorcodes.newsapp.presentation.onboarding.OnboardingScreen
import ru.igorcodes.newsapp.presentation.onboarding.components.OnboardingPage
import ru.igorcodes.newsapp.ui.theme.NewsAppTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        showSplashScreenAfter()

        setContent {
            NewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    OnboardingScreen()
                }
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
