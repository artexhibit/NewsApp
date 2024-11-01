package ru.igorcodes.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import ru.igorcodes.newsapp.R
import ru.igorcodes.newsapp.ui.theme.NewsAppTheme
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    var message = remember { mutableStateOf(parseErrorMessage(error)) }
    var icon = remember { mutableIntStateOf(R.drawable.ic_network_error) }
    var startAnimation = remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation.value) 0.3f else 0f,
        animationSpec = tween(1500), label = ""
    )

    if (error == null) {
        message.value = "You have not saved news so far!"
        icon.intValue = R.drawable.ic_search_document
    }

    LaunchedEffect(key1 = true) { startAnimation.value = true }
    EmptyContent(alphaAnimation.value, message.value, icon.intValue)
}

@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )

        Text(
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
    }
}

private fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }

        is ConnectException -> {
            "Internet Unavailable"
        }

        else -> {
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    NewsAppTheme {
        EmptyContent(0.3f, "Internet Unavailable", R.drawable.ic_network_error)
    }
}