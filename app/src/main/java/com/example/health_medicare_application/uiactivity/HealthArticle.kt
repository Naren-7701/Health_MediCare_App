package com.example.health_medicare_application.uiactivity

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.health_medicare_application.BottomBar
import com.example.health_medicare_application.TopApplicationBar
import com.example.health_medicare_application.ui.theme.fillmaxwid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthArticlePage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("HEALTH ARTICLE",navController) },
        content = {pad -> HealthNews(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HealthNews(h:PaddingValues) {
    Column(
        modifier = fillmaxwid.fillMaxSize().background(color = Color.White),
    )
    {
        val url = "https://www.medicalnewstoday.com/news"
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}