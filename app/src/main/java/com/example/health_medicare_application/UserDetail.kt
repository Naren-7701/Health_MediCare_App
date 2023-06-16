package com.example.health_medicare_application

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailPage(context: Context, navController: NavController)
{
    Scaffold(
        topBar = { TopBar("BASIC USER DETAIL") },
        content = {pad -> UserDetailFill(pad,context,navController) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailFill(h: PaddingValues,context: Context,navController: NavController) {
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
        val height = remember { mutableStateOf(TextFieldValue("")) }
        val weight = remember { mutableStateOf(TextFieldValue("")) }
        val age = remember { mutableStateOf(TextFieldValue("")) }
        val maxageLength = 3
        var gen by remember { mutableStateOf("") }
        var blg by remember { mutableStateOf("") }
        val systolbp = remember { mutableStateOf(TextFieldValue("")) }
        val diastolbp = remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = height.value,
            onValueChange = {
                height.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Height (in cm)") },
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.ArrowUpward, contentDescription = null) }
        )
        TextField(
            value = weight.value,
            onValueChange = {
                weight.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Weight (in kg)") },
            modifier = Modifier.fillMaxWidth().border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.Scale, contentDescription = null) }
        )
        TextField(
            value = age.value,
            onValueChange = {
                if (it.text.length <= maxageLength)
                    age.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Age") },
            modifier = Modifier.fillMaxWidth().border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = null) }
        )
        blg = bloodGroup()
        gen = gender()
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = "Enter Blood Pressure",
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            TextField(
                value = systolbp.value,
                onValueChange = {
                    systolbp.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                label = { Text("Systol") },
                modifier = Modifier.width(150.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            TextField(
                value = diastolbp.value,
                onValueChange = {
                    diastolbp.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                label = { Text("Diastol") },
                modifier = Modifier.width(150.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
        }
        Button(
            onClick = {
                navController.navigate("dashboard")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "SUBMIT DETAILS",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
@Composable
fun gender():String {
    val radioOptions = listOf("Male", "Female", "Other")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }
    Column {
        Row {
            radioOptions.forEach { text ->
                Row(
                    Modifier.selectable(selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        },
                        colors = RadioButtonDefaults.colors(Color(0xFF673AB7))
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
            }
        }
    }
    return selectedOption
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bloodGroup():String {
    var isexpanded by remember { mutableStateOf(false) }
    var bloodgrp by remember { mutableStateOf("") }
    val bloodgroups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    ExposedDropdownMenuBox(expanded = isexpanded,
        onExpandedChange = { isexpanded = it })
    {
        TextField(
            value = bloodgrp,
            onValueChange = {},
            readOnly = true,
            leadingIcon = { Icon(Icons.Outlined.WaterDrop, contentDescription = null) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isexpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
                .menuAnchor()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            placeholder = { Text(text = "Select Blood Group") },
        )
        ExposedDropdownMenu(
            expanded = isexpanded,
            onDismissRequest = { isexpanded = false },
            modifier = Modifier.background(color = Color.White)
        )
        {
            bloodgroups.forEach { bloodgroups ->
                DropdownMenuItem(
                    text = { Text(text = bloodgroups) },
                    onClick = {
                        bloodgrp = bloodgroups
                        isexpanded = false
                    })
            }
        }
    }
    return bloodgrp
}