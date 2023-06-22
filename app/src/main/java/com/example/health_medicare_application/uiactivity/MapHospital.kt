package com.example.health_medicare_application.uiactivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.health_medicare_application.BottomBar
import com.example.health_medicare_application.TopApplicationBar
import com.example.health_medicare_application.ui.theme.fillmaxwid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("MAPS SEARCH",navController) },
        content = {pad -> GmapPage(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@Composable
fun GmapPage(h: PaddingValues) {
    Column(
        modifier = fillmaxwid.fillMaxSize().background(color = Color.White),
    )
    {}
}
