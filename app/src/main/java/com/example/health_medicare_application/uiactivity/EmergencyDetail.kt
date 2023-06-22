package com.example.health_medicare_application.uiactivity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health_medicare_application.BottomBar
import com.example.health_medicare_application.TopApplicationBar
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.fnt20
import com.example.health_medicare_application.ui.theme.fnt21
import com.example.health_medicare_application.ui.theme.iconsize
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.subtxtcol
import com.example.health_medicare_application.ui.theme.subtxtsize
import com.example.health_medicare_application.ui.theme.txtbold
import com.example.health_medicare_application.ui.theme.vertspace
import com.example.health_medicare_application.ui.theme.wid150

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactPage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar(abc = "EMERGENCY DIAL",navController) },
        content = {pad -> EmergencyContactPageInfo(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@Composable
fun EmergencyContactPageInfo(h:PaddingValues) {
    Column(
        modifier = Activityscreen,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = vertspace,
    )
    {
        val context = LocalContext.current
        Text(
            text = "Important Emergency Contacts",
            fontSize = fnt21,
            fontWeight = txtbold,
            modifier = fillmaxwid.padding(top = 25.dp),
        )
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Police \n" + "100",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(100, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(100, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Fire Extinguish \n" + "101",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(101, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(101, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Ambulance \n" + "102",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(102, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(102, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Rail Accident \n" + "1072",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(1072, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(1072, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Road Accident \n" + "1073",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(1073, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(1073, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = vertspace,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Women Help \n" + "1091",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = wid150
            )
            IconButton(
                onClick = { call(1091, context) }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = purple673,
                    modifier = iconsize
                )
            }
            IconButton(
                onClick = {
                    message(1091, context)
                },
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = purple673,
                    modifier = iconsize
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = fillmaxwid
        ) {
            Text(
                text = "Contact them whenever Necessary",
                color = subtxtcol,
                fontSize = subtxtsize,
                fontWeight = txtbold,
            )
        }
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