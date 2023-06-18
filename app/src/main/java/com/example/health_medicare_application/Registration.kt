package com.example.health_medicare_application

import android.content.Context
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPage(context: Context, navController: NavController,databaseReference: DatabaseReference,databaseHelper: UserDatabaseHelper)
{
    Scaffold(
        topBar = { TopBar("REGISTRATION") },
        content = {pad -> RegisterFill(pad,context,navController,databaseReference,databaseHelper) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFill(h: PaddingValues,context: Context,navController: NavController,databaseReference: DatabaseReference,databaseHelper: UserDatabaseHelper) {
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
        val regemail = remember { mutableStateOf(TextFieldValue("")) }
        val usrmailname = remember { mutableStateOf("") }
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val mobile = remember { mutableStateOf(TextFieldValue("")) }
        val maxmobileLength = 10
        val regpw = remember { mutableStateOf(TextFieldValue("")) }
        val passwordVisible1 = remember { mutableStateOf(false) }
        val height = remember { mutableStateOf(TextFieldValue("")) }
        val weight = remember { mutableStateOf(TextFieldValue("")) }
        val age = remember { mutableStateOf(TextFieldValue("")) }
        val maxageLength = 3
        var gen by remember { mutableStateOf("") }
        var blg by remember { mutableStateOf("") }
        val systolbp = remember { mutableStateOf(TextFieldValue("")) }
        val diastolbp = remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = regemail.value,
            onValueChange = {
                regemail.value = it
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Email ID") },
            modifier = Modifier
                .fillMaxWidth().padding(top=50.dp)
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
        usrmailname.value = regemail.value.text.substringBefore("@")
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Full Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) }
        )
        TextField(
            value = mobile.value,
            onValueChange = {
                if (it.text.length <= maxmobileLength) {
                    mobile.value = it
                }
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Mobile Number") },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.Phone, contentDescription = null) }
        )
        TextField(
            value = regpw.value,
            onValueChange = {
                regpw.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Password") },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            visualTransformation = if (passwordVisible1.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (passwordVisible1.value) {
                    IconButton(onClick = { passwordVisible1.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "show_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { passwordVisible1.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        )
        {
            TextField(
                value = height.value,
                onValueChange = {
                    height.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                label = { Text("Height") },
                placeholder={Text("cm")},
                modifier = Modifier.width(90.dp)
                    .border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            TextField(
                value = weight.value,
                onValueChange = {
                    weight.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                label = { Text("Weight") },
                placeholder={Text("kg")},
                modifier = Modifier.width(90.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            TextField(
                value = age.value,
                onValueChange = {
                    if (it.text.length <= maxageLength)
                        age.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                label = { Text("Age") },
                modifier = Modifier.width(80.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
        }
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
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        )
        {
            TextField(
                value = systolbp.value,
                onValueChange = {
                    systolbp.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                placeholder={Text("Systol")},
                modifier = Modifier.width(100.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            TextField(
                value = diastolbp.value,
                onValueChange = {
                    diastolbp.value = it
                },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                placeholder={Text("Diastol")},
                modifier = Modifier.width(100.dp).border(BorderStroke(2.dp, Color(0xFF673AB7))),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
        }
        Button(
            onClick = {
                val userObj =
                    UserObject(
                        regemail.value.text,
                        mobile.value.text,
                        regpw.value.text,
                        name.value.text
                    )
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseReference.child(usrmailname.value+"").setValue(userObj)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            context, "Registration UnSuccessful ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                });
                if (name.value.text.isNotEmpty() && regpw.value.text.isNotEmpty() && mobile.value.text.isNotEmpty() && regemail.value.text.isNotEmpty()) {
                    val user = UserDetailDb(
                        id = null,
                        email = regemail.value.text,
                        mobile = mobile.value.text,
                        password = regpw.value.text,
                        name = name.value.text,
                        bmi = (weight.value.text.toFloat() * 10000 / (height.value.text.toFloat() * height.value.text.toFloat())).toInt(),
                        age = age.value.text,
                        gender = gen,
                        bloodgrp = blg,
                        bloodpres = systolbp.value.text+" / "+diastolbp.value.text
                    )
                    databaseHelper.insertUser(user)
                    navController.navigate("dashboard/${regemail.value.text}")
                } else {
                    Toast.makeText(
                        context, "Please fill all the Details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "REGISTER / SIGN UP", color = Color.White, fontSize = 20.sp,
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