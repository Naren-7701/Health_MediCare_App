package com.example.health_medicare_application

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.purewhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) = Box(fillmaxwid.fillMaxHeight().background(color = purewhite))
{
    val scale = remember { androidx.compose.animation.core.Animatable(0.0f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(800)
        navController.navigate("login")
    }
    Image(
        painter = painterResource(id = R.drawable.health_medicare_app),
        contentDescription = "logo",
        alignment = Alignment.Center, modifier = Modifier
            .fillMaxSize().padding(30.dp).scale(scale.value)
    )
}