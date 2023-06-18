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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bloodtype
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Person4
import androidx.compose.material.icons.outlined.Scale
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
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            val bmi = user.bmi
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = user.name.toString().uppercase(),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Mobile",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = user.mobile.toString().uppercase(),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person4,
                    contentDescription = "Sex",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                if (user.gender == "Male") {
                    Image(
                        painter = painterResource(id = R.drawable.men),
                        contentDescription = "Men",
                        modifier = Modifier.size(30.dp)
                    )
                } else if (user.gender == "Female") {
                    Image(
                        painter = painterResource(id = R.drawable.women),
                        contentDescription = "Women",
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tg),
                        contentDescription = "Transgender",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Age",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = user.age.toString(), color = Color.Black, fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Icon(
                    imageVector = Icons.Outlined.Bloodtype,
                    contentDescription = "Bloodgroup",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = user.bloodgrp.toString(),
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Scale,
                    contentDescription = "Sex",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = bmi.toString(), color = Color.Black, fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))
                if (bmi != null) {
                    if (bmi.toFloat() < 18.5) {
                        Text(
                            text = "(UNDER-WEIGHT)",
                            color = Color(0xFF3F51B5),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi.toFloat() in 18.5..24.9) {
                        Text(
                            text = "(NORMAL)",
                            color = Color(0xFF028B7F),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi.toFloat() in 25.0..29.9) {
                        Text(
                            text = "(OVER-WEIGHT)",
                            color = Color(0xFFB8A81E),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (bmi.toFloat() in 30.0..34.9) {
                        Text(
                            text = "(OBESE)",
                            color = Color(0xFFFF9800),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "(EXTREMELY OBESE)",
                            color = Color.Red,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Icon(
                    imageVector = Icons.Outlined.MonitorHeart,
                    contentDescription = "Blood pressure",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = user.bloodpres.toString(), color = Color.Black, fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "Be Cautious about your Weight !",
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.bmi_chart),
                contentDescription = "BMI",
                modifier = Modifier.width(400.dp).height(200.dp)
            )
        }
    }
}