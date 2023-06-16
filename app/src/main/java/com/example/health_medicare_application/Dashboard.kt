package com.example.health_medicare_application

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(context: Context, navController: NavController)
{
    Scaffold(
        topBar = { TopDashboardBar("DASHBOARD") },
        content = {pad -> Greeting(pad,context,navController) },
        bottomBar = { BottomBar(navController)}
    )
}
@Composable
fun Greeting(h: PaddingValues, context: Context, navController: NavController)
{

}
