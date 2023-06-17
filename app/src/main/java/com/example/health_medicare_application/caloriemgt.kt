package com.example.health_medicare_application

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriePage(context: Context, navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("CALORIE MANAGE","dashboard/{email}",navController) },
        content = {pad -> Calorie(pad,context,navController) },
        bottomBar = { BottomBar(navController)}
    )
}
@Composable
fun Calorie(h: PaddingValues, context: Context, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        val bmi = remember { mutableStateOf(0f) }
        Text(
            text = "Your Current BMI: " + bmi.value,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 35.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
        {
            if (bmi.value < 18.5) {
                Text(
                    text = "UNDER-WEIGHT",
                    color = Color.Blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } else if (18.5 < bmi.value && bmi.value < 24.9) {
                Text(
                    text = "NORMAL",
                    color = Color.Green,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } else if (25.0 < bmi.value && bmi.value < 29.9) {
                Text(
                    text = "OVER-WEIGHT",
                    color = Color(0xFFDDC918),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } else if (30.0 < bmi.value && bmi.value < 34.9) {
                Text(
                    text = "OBESE",
                    color = Color(0xFFFF9800),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "EXTREMELY OBESE",
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
