package com.example.health_medicare_application.uiactivity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health_medicare_application.TopBar
import com.example.health_medicare_application.model.MedicalDatabaseHelper
import com.example.health_medicare_application.model.MedicalDb
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.fnt18
import com.example.health_medicare_application.ui.theme.horzcenter
import com.example.health_medicare_application.ui.theme.mobkeypad
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.rcshape
import com.example.health_medicare_application.ui.theme.reglogbut
import com.example.health_medicare_application.ui.theme.reglogbuttxtcol
import com.example.health_medicare_application.ui.theme.subtxtcol
import com.example.health_medicare_application.ui.theme.txtbold
import com.example.health_medicare_application.ui.theme.txtcenter
import com.example.health_medicare_application.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalRegPage(context: Context, navController: NavController,databaseHelper:MedicalDatabaseHelper)
{
    Scaffold(
        topBar = { TopBar("MEDICAL DETAILS") },
        content = {pad -> MedicalRegFill(pad,context,navController,databaseHelper) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalRegFill(h: PaddingValues, context: Context, navController: NavController, databaseHelper: MedicalDatabaseHelper) {
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
        val email = remember { mutableStateOf(TextFieldValue("")) }
        val ht = remember { mutableStateOf(TextFieldValue("")) }
        val wt = remember { mutableStateOf(TextFieldValue("")) }
        val age = remember { mutableStateOf(TextFieldValue("")) }
        val blg = remember { mutableStateOf(0) }
        val gen = remember { mutableStateOf("") }
        val groups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        val categories = listOf("Allergy, Immunology","Cardiologist","Dentist","Dermatologist","ENT Specialist",
            "Gynaecologist","Nephrology","Neurology","Ophthalmologist", "Orthopedic","Pathology","Pediatrics",
            "Psychiatrist","Radiation Oncology","Surgery","Urology")
        val categ = remember { mutableStateOf(0) }
        val location = remember { mutableStateOf(TextFieldValue("")) }
        val systol = remember { mutableStateOf(TextFieldValue("")) }
        val diastol = remember { mutableStateOf(TextFieldValue("")) }
        val txtfieldcol = TextFieldDefaults.textFieldColors(containerColor = Color.White)
        Text(
            text = "We want to know more about You", fontSize = fnt18, color = subtxtcol,
            fontWeight = txtbold, modifier = Modifier.padding(top=30.dp)
        )
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("Enter Email ID") },
            modifier = fillmaxwid.border(BorderStroke(2.dp, purple673)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = fillmaxwid
        )
        {
            TextField(
                value = ht.value,
                onValueChange = {
                    ht.value = it
                },
                colors = txtfieldcol,
                label = { Text("Height") },
                placeholder = { Text("cm") },
                modifier = Modifier.width(86.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
            TextField(
                value = wt.value,
                onValueChange = {
                    wt.value = it
                },
                colors = txtfieldcol,
                label = { Text("Weight") },
                placeholder = { Text("kg") },
                modifier = Modifier.width(86.dp).border(BorderStroke(2.dp, purple673)),
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
                modifier = Modifier.width(86.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
        }
        blg.value = dropselector(Icons.Outlined.WaterDrop,groups, "Blood-Grp", fillmaxwid)
        TextField(
            value = location.value,
            onValueChange = {
                location.value = it
            },
            colors = txtfieldcol,
            label = { Text("Enter Location") },
            leadingIcon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = null) },
            modifier = fillmaxwid.border(BorderStroke(2.dp, purple673)),
        )
        gen.value = gender()
        categ.value = dropselector(Icons.Outlined.MedicalServices,categories, "Select Preferred Category", fillmaxwid)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = fillmaxwid
        )
        {
            TextField(
                value = systol.value,
                onValueChange = {
                    systol.value = it
                },
                colors = txtfieldcol,
                placeholder = { Text("BP (Systol)") },
                modifier = Modifier.width(130.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
            TextField(
                value = diastol.value,
                onValueChange = {
                    diastol.value = it
                },
                colors = txtfieldcol,
                placeholder = { Text("BP (Diastol)") },
                modifier = Modifier.width(130.dp).border(BorderStroke(2.dp, purple673)),
                keyboardOptions = mobkeypad,
            )
        }
        Button(
            onClick = {
                if (ht.value.text.isNotEmpty() && wt.value.text.isNotEmpty() && age.value.text.isNotEmpty()
                    && location.value.text.isNotEmpty() && gen.value.isNotEmpty()
                ) {
                    val usermed = MedicalDb(
                        id = null,
                        email = email.value.text,
                        location = location.value.text,
                        bmi = (wt.value.text.toFloat() * 10000 / (ht.value.text.toFloat() * ht.value.text.toFloat())).toInt(),
                        age = age.value.text,
                        gender = gen.value,
                        bloodgrp = groups.get(blg.value),
                        bloodpres = systol.value.text + " / " + diastol.value.text,
                        category = categories.get(categ.value)
                    )
                    databaseHelper.insertnewUser(usermed)
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
        ) {
            Text(
                text = "SUBMIT MEDICAL DETAILS",
                color = reglogbuttxtcol,
                fontSize = reglogbut,
                textAlign = txtcenter
            )
        }
    }
}
@Composable
fun gender():String {
    val sex = listOf("Male", "Female", "Other")
    val (selected, onOptionSelected) = remember { mutableStateOf(sex[2]) }
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