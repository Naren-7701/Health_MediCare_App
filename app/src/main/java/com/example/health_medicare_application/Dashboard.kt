package com.example.health_medicare_application

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(context: Context, navController: NavController,databaseHelper: UserDatabaseHelper,email:String?)
{
    Scaffold(
        topBar = { TopDashboardBar("DASHBOARD") },
        content = {pad -> DashboardContent(pad,context,navController,databaseHelper,email) },
        bottomBar = { BottomBar(navController)}
    )
}
@Composable
fun DashboardContent(h: PaddingValues, context: Context, navController: NavController,databaseHelper: UserDatabaseHelper,email:String?) {
    val user = databaseHelper.getUserByUseremail(email.toString())
    if (user != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(color = Color.White)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            val bmi = user.bmi
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp)
            )
            {
                Card(modifier=Modifier.size(84.dp))
                {
                    if (user.gender == "Male") {
                        Image(
                            painter = painterResource(id = R.drawable.men),
                            contentDescription = "Men",
                            modifier = Modifier.size(80.dp).padding(start = 2.dp,top=2.dp)
                        )
                    } else if (user.gender == "Female") {
                        Image(
                            painter = painterResource(id = R.drawable.women),
                            contentDescription = "Women",
                            modifier = Modifier.size(80.dp).padding(start = 2.dp,top=2.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.tg),
                            contentDescription = "Transgender",
                            modifier = Modifier.size(80.dp).padding(start = 2.dp,top=2.dp)
                        )
                    }
                }
                Text(
                    text = user.name.toString().uppercase(), color = Color.Black, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "BMI : ", color = Color(0xFF673AB7), fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = bmi.toString(), color = Color.Black, fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                if (bmi != null) {
                    if (bmi < 18.5) {
                        Text(
                            text = "UNDER-WEIGHT",
                            color = Color(0xFF3F51B5),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi in 18.5..24.9) {
                        Text(
                            text = "NORMAL",
                            color = Color(0xFF028B7F),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi in 25.0..29.9) {
                        Text(
                            text = "OVER-WEIGHT",
                            color = Color(0xFFB8A81E),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi in 30.0..34.9) {
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
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Age",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = " "+user.age.toString(), color = Color.Black, fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Icon(
                    imageVector = Icons.Outlined.WaterDrop,
                    contentDescription = "Bloodgroup",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = " "+user.bloodgrp.toString(), color = Color.Black, fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "BP : ", color = Color(0xFF673AB7), fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user.bloodpres.toString(), color = Color.Black, fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}