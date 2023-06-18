package com.example.health_medicare_application

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactPage(navController: NavController)
{
    Scaffold(
        topBar = { TopBar("EMERGENCY DIAL") },
        content = {pad -> EmergencyContactPageInfo(pad) },
        bottomBar = { BottomBar(navController)}
    )
}

@Composable
fun EmergencyContactPageInfo(h:PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(25.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
    )
    {
        val context = LocalContext.current
        Text(
            text = "Important Emergency Contacts",
            color = Color.Black,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(top=25.dp),
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Police \n"+"100",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(100, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(100,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Fire Extinguish \n"+"101",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(101, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(101,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Ambulance \n"+"102",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(102, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(102,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Rail Accident \n"+"1072",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(1072,context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(1072,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Road Accident \n"+"1073",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(1073, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(1073,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Women Help \n"+"1091",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(150.dp)
            )
            IconButton(
                onClick = { call(1091, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = {
                    message(1091,context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = Color(0xFF673AB7),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Text(
            text = "Contact them whenever Necessary",
            color = Color.DarkGray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
fun call(number:Int,context:Context) {
    val u = Uri.parse("tel:" + number)
    val inten = Intent(Intent.ACTION_DIAL, u)
    try {
        context.startActivity(inten)
    } catch (s: SecurityException) {
        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
    }
}
fun message(number:Int,context:Context) {
    val msg = Uri.parse("sms:" + number)
    val msginten = Intent(Intent.ACTION_VIEW, msg)
    try {
        context.startActivity(msginten)
    } catch (s: SecurityException) {
        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
    }
}