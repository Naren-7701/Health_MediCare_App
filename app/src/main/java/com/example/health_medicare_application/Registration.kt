package com.example.health_medicare_application

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.boxes
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.fnt20
import com.example.health_medicare_application.ui.theme.horzcenter
import com.example.health_medicare_application.ui.theme.horzspacear
import com.example.health_medicare_application.ui.theme.purewhite
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.rcshape
import com.example.health_medicare_application.ui.theme.subtxtcol
import com.example.health_medicare_application.ui.theme.subtxtsize
import com.example.health_medicare_application.ui.theme.txtbold
import com.example.health_medicare_application.ui.theme.vertspace
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
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
        val email = remember { mutableStateOf(TextFieldValue("")) }
        val usrname = remember { mutableStateOf("") }
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val mobile = remember { mutableStateOf(TextFieldValue("")) }
        val regpw = remember { mutableStateOf(TextFieldValue("")) }
        val pwvisib = remember { mutableStateOf(false) }
        val height = remember { mutableStateOf(TextFieldValue("")) }
        val weight = remember { mutableStateOf(TextFieldValue("")) }
        val age = remember { mutableStateOf(TextFieldValue("")) }
        var gen by remember { mutableStateOf("") }
        var blg by remember { mutableStateOf("") }
        val systolbp = remember { mutableStateOf(TextFieldValue("")) }
        val diastolbp = remember { mutableStateOf(TextFieldValue("")) }
        val txtfieldcol = TextFieldDefaults.textFieldColors(containerColor = Color.White)
        val mobkeypad = KeyboardOptions(keyboardType = KeyboardType.Phone)
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("Enter Email ID") },
            modifier = fillmaxwid.padding(top = 50.dp).border(BorderStroke(2.dp, purple673)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
        usrname.value = email.value.text.substringBefore("@")
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            colors = txtfieldcol,
            label = { Text("Enter Full Name") },
            singleLine = true,
            modifier = boxes,
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) }
        )
        TextField(
            value = mobile.value,
            onValueChange = {
                if (it.text.length <= 10) {
                    mobile.value = it
                }
            },
            colors = txtfieldcol,
            label = { Text("Enter Mobile Number") },
            modifier = boxes,
            keyboardOptions = mobkeypad,
            leadingIcon = { Icon(Icons.Outlined.Phone, contentDescription = null) }
        )
        TextField(
            value = regpw.value,
            onValueChange = {
                regpw.value = it
            },
            colors = txtfieldcol,
            label = { Text("Enter Password") },
            modifier = boxes,
            visualTransformation = if (pwvisib.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (pwvisib.value) {
                    IconButton(onClick = { pwvisib.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "show_pw"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { pwvisib.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_pw"
                        )
                    }
                }
            }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = fillmaxwid
        )
        {
            TextField(
                value = height.value,
                onValueChange = {
                    height.value = it
                },
                colors = txtfieldcol,
                label = { Text("Height") },
                placeholder = { Text("cm") },
                modifier = Modifier.width(90.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
            TextField(
                value = weight.value,
                onValueChange = {
                    weight.value = it
                },
                colors = txtfieldcol,
                label = { Text("Weight") },
                placeholder = { Text("kg") },
                modifier = Modifier.width(90.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
            TextField(
                value = age.value,
                onValueChange = {
                    if (it.text.length <= 3)
                        age.value = it
                },
                colors = txtfieldcol,
                label = { Text("Age") },
                modifier = Modifier.width(80.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
        }
        blg = bloodGroup()
        gen = gender()
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = fillmaxwid
        )
        {
            Text(
                text = "Enter Blood Pressure",
                color = subtxtcol,
                fontSize = subtxtsize,
                fontWeight = txtbold
            )
        }
        Row(
            horizontalArrangement = horzspacear,
            modifier = fillmaxwid
        )
        {
            TextField(
                value = systolbp.value,
                onValueChange = {
                    systolbp.value = it
                },
                colors = txtfieldcol,
                placeholder = { Text("Systol") },
                modifier = Modifier.width(100.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
            TextField(
                value = diastolbp.value,
                onValueChange = {
                    diastolbp.value = it
                },
                colors = txtfieldcol,
                placeholder = { Text("Diastol") },
                modifier = Modifier.width(100.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
        }
        Button(
            onClick = {
                val userObj =
                    UserObject(
                        email.value.text,
                        mobile.value.text,
                        regpw.value.text,
                        name.value.text
                    )
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseReference.child(usrname.value + "").setValue(userObj);
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            context, "Registration UnSuccessful ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                });
                if (name.value.text.isNotEmpty() && regpw.value.text.isNotEmpty() && mobile.value.text.isNotEmpty() && email.value.text.isNotEmpty()) {
                    val user = UserDetailDb(
                        id = null,
                        email = email.value.text,
                        mobile = mobile.value.text,
                        password = regpw.value.text,
                        name = name.value.text,
                        bmi = (weight.value.text.toFloat() * 10000 / (height.value.text.toFloat() * height.value.text.toFloat())).toInt(),
                        age = age.value.text,
                        gender = gen,
                        bloodgrp = blg,
                        bloodpres = systolbp.value.text + " / " + diastolbp.value.text
                    )
                    databaseHelper.insertUser(user)
                    navController.navigate("dashboard/${email.value.text}")
                } else {
                    Toast.makeText(
                        context, "Please fill All the Details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = fillmaxwid,
            colors = ButtonDefaults.buttonColors(purple673),
            shape = rcshape
        )
        {
            Text(
                text = "REGISTER / SIGN UP", color = purewhite, fontSize = fnt20
            )
        }
    }
}
@Composable
fun gender():String {
    val sex = listOf("Male", "Female", "Other")
    val (selected, onOptionSelected) = remember { mutableStateOf(sex[2])}
    Row {
        sex.forEach { text ->
            Row(
                Modifier.selectable(selected = (text == selected),
                    onClick = { onOptionSelected(text) }
                )
            ) {
                RadioButton(
                    selected = (text == selected),
                    onClick = {
                        onOptionSelected(text)
                    },
                    colors = RadioButtonDefaults.colors(purple673)
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
        }
    }
    return selected
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bloodGroup():String {
    var isexpand by remember { mutableStateOf(false) }
    var blgrp by remember { mutableStateOf("") }
    val groups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    ExposedDropdownMenuBox(expanded = isexpand,
        onExpandedChange = { isexpand = it })
    {
        TextField(
            value = blgrp,
            onValueChange = {},
            readOnly = true,
            leadingIcon = { Icon(Icons.Outlined.WaterDrop, contentDescription = null) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isexpand) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = purewhite),
            modifier = fillmaxwid.menuAnchor().border(BorderStroke(2.dp, purple673)),
            placeholder = { Text(text = "Select Blood Group") },
        )
        ExposedDropdownMenu(
            expanded = isexpand,
            onDismissRequest = { isexpand = false },
            modifier = Modifier.background(color = purewhite)
        )
        {
            groups.forEach { bloodgroups ->
                DropdownMenuItem(
                    text = { Text(text = bloodgroups) },
                    onClick = {
                        blgrp = bloodgroups
                        isexpand = false
                    })
            }
        }
    }
    return blgrp
}