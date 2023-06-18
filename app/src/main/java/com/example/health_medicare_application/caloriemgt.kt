package com.example.health_medicare_application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.horzcenter
import com.example.health_medicare_application.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriePage(navController: NavController)
{
    Scaffold(
        topBar = { TopBar("CALORIE MANAGE") },
        content = {pad -> Calorie(pad) },
        bottomBar = { BottomBar(navController)}
    )
}
@Composable
fun Calorie(h:PaddingValues) {
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
    }
}
