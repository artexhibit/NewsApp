package ru.igorcodes.newsapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.igorcodes.newsapp.presentation.navgraph.NavGraph
import ru.igorcodes.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        showSplashScreenAfter()

        setContent {
            NewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }

    private fun showSplashScreenAfter(seconds: Long = 2000) {
        val splashScreen = installSplashScreen()
        var isSplashScreenVisible = true

        splashScreen.setKeepOnScreenCondition {
            isSplashScreenVisible
            viewModel.splashCondition
        }

        lifecycleScope.launch {
            delay(seconds)
            isSplashScreenVisible = false
        }
    }
}
