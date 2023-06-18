package com.example.health_medicare_application

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.fnt22
import com.example.health_medicare_application.ui.theme.fnt24
import com.example.health_medicare_application.ui.theme.horzcenter
import com.example.health_medicare_application.ui.theme.horzstart
import com.example.health_medicare_application.ui.theme.iconsize
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.subtxtcol
import com.example.health_medicare_application.ui.theme.subtxtsize
import com.example.health_medicare_application.ui.theme.txtbold
import com.example.health_medicare_application.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(navController: NavController,databaseHelper: UserDatabaseHelper,email:String?)
{
    Scaffold(
        topBar = { TopBar("DASHBOARD") },
        content = {pad -> DashboardContent(pad,databaseHelper,email) },
        bottomBar = { BottomBar(navController)}
    )
}
@Composable
fun DashboardContent(h: PaddingValues,databaseHelper: UserDatabaseHelper,email:String?) {
    val sp5 = Modifier.padding(5.dp)
    val user = databaseHelper.getUserByUseremail(email.toString())
    if (user != null) {
        Column(
            modifier = Activityscreen,
            horizontalAlignment = horzcenter,
            verticalArrangement = vertspace
        )
        {
            val bmi = user.bmi
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid.padding(top = 30.dp)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = user.name.toString().uppercase(),
                    fontSize = fnt24,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Mobile",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = user.mobile.toString().uppercase(),
                    fontSize = fnt24,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid,
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person4,
                    contentDescription = "Sex",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                if (user.gender == "Male") {
                    Image(
                        painter = painterResource(id = R.drawable.men),
                        contentDescription = "Men",
                        modifier = iconsize
                    )
                } else if (user.gender == "Female") {
                    Image(
                        painter = painterResource(id = R.drawable.women),
                        contentDescription = "Women",
                        modifier = iconsize
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tg),
                        contentDescription = "Transgender",
                        modifier = iconsize
                    )
                }
                Spacer(modifier = sp5)
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Age",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = user.age.toString(), fontSize = fnt22,
                    fontWeight = txtbold
                )
                Spacer(modifier = sp5)
                Icon(
                    imageVector = Icons.Outlined.Bloodtype,
                    contentDescription = "Bloodgroup",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = user.bloodgrp.toString(),
                    fontSize = fnt22,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid,
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Scale,
                    contentDescription = "Sex",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = bmi.toString(), fontSize = fnt22,
                    fontWeight = txtbold
                )
                Spacer(modifier = sp5)
                if (bmi != null) {
                    if (bmi.toFloat() < 18.5) {
                        Text(
                            text = "(UNDER-WEIGHT)",
                            color = Color(0xFF3F51B5),
                            fontSize = fnt22,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 18.5..24.9) {
                        Text(
                            text = "(NORMAL)",
                            color = Color(0xFF028B7F),
                            fontSize = fnt22,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 25.0..29.9) {
                        Text(
                            text = "(OVER-WEIGHT)",
                            color = Color(0xFFB8A81E),
                            fontSize = fnt22,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 30.0..34.9) {
                        Text(
                            text = "(OBESE)",
                            color = Color(0xFFFF9800),
                            fontSize = fnt22,
                            fontWeight = txtbold
                        )
                    } else {
                        Text(
                            text = "(EXTREMELY OBESE)",
                            color = Color.Red,
                            fontSize = fnt22,
                            fontWeight = txtbold
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.MonitorHeart,
                    contentDescription = "Blood pressure",
                    tint = purple673,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = user.bloodpres.toString(), fontSize = 21.sp,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = iconsize
            )
            {
                Text(
                    text = "Be Cautious about your Weight !",
                    color = subtxtcol,
                    fontSize = subtxtsize,
                    fontWeight = txtbold,
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