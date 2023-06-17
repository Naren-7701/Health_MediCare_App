package com.example.health_medicare_application

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        topBar = { TopApplicationBar("EMERGENCY DIAL","dashboard/{email}",navController) },
        content = {pad -> ListView(pad) },
        bottomBar = { BottomBar(navController)}
    )
}
data class MobileNumber(val name:String, val number:Int)
val Numbers = mutableListOf<MobileNumber>()

@Composable
fun ListView(h:PaddingValues) {
    Numbers.add(MobileNumber("Police Station",100))
    Numbers.add(MobileNumber("Fire Extinguish",101))
    Numbers.add(MobileNumber("Ambulance Help",102))
    Numbers.add(MobileNumber("Rail Accident",1072))
    Numbers.add(MobileNumber("Road Accident",1073))
    Numbers.add(MobileNumber("Women Helpline",1091))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(25.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    )
    {
        Row(horizontalArrangement = Arrangement.Center)
        {

            Text(
                text = "Important Emergency Contacts",
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            )
        }
        LazyColumn() {
            items(Numbers) { model ->
                EmergencyDial(model = model)
            }
        }
    }
}
@Composable
fun EmergencyDial(model: MobileNumber) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp)
    ) {
        val context = LocalContext.current
        Text(
            text = model.name,
            color = Color.Black,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(150.dp)
        )
        IconButton(
            onClick = {
                val u = Uri.parse("tel:" + model.number)
                val inten = Intent(Intent.ACTION_DIAL, u)
                try {
                    context.startActivity(inten)
                } catch (s: SecurityException) {
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            },
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
                val msg = Uri.parse("sms:" + model.number)
                val msginten = Intent(Intent.ACTION_VIEW, msg)
                try {
                    context.startActivity(msginten)
                } catch (s: SecurityException) {
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }
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
    Row(horizontalArrangement = Arrangement.Start)
    {
        Text(
            text = "" + model.number,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}