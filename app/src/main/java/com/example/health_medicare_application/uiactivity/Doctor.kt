package com.example.health_medicare_application.uiactivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.health_medicare_application.BottomBar
import com.example.health_medicare_application.TopBar
import com.example.health_medicare_application.model.UserDatabaseHelper
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Doctor(navController: NavController, databaseHelper: UserDatabaseHelper,/* email:String?*/)
{
    Scaffold(
        topBar = { TopBar("DOCTOR SEARCH") },
        content = {pad -> DoctorSearch(pad,databaseHelper) },
        bottomBar = { BottomBar(navController) }
    )
}
@Composable
fun DoctorSearch(h: PaddingValues, databaseHelper: UserDatabaseHelper,/*email:String?*/) {
    Column(
        modifier = fillmaxwid,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = vertspace
    )
    {
        // Location wise
    }
}