package com.example.health_medicare_application

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIPage(context: Context, navController: NavController) {
    Scaffold(
        topBar = { TopBar("BMI CALCULATOR") },
        content = { pad -> BMICalculator(pad, context, navController) },
        bottomBar = { BottomBar(navController = navController) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator(h: PaddingValues, context: Context, navController: NavController) {
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
        var ht = remember { mutableStateOf(TextFieldValue("")) }
        var wt = remember { mutableStateOf(TextFieldValue("")) }
        var bmi = remember { mutableStateOf(0f) }
        var visible by remember { mutableStateOf(false) }
        TextField(
            value = ht.value,
            onValueChange = {
                ht.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter New Height (in cm)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.ArrowUpward, contentDescription = null) }
        )
        TextField(
            value = wt.value,
            onValueChange = {
                wt.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter New Weight (in kg)") },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Outlined.Scale, contentDescription = null) })
        Button(
            onClick = {
                bmi.value =
                    wt.value.text.toFloat() * 10000 / (ht.value.text.toFloat() * ht.value.text.toFloat());
                visible = !visible
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "CALCULATE BMI", color = Color.White, fontSize = 20.sp
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
        {
            if (visible) {
                Text(
                    text = "New BMI : " + bmi.value.toString(),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
        {
            if (visible) {
                if (bmi.value < 18.5) {
                    Text(
                        text = "UNDER-WEIGHT",
                        color = Color(0xFF3F51B5),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (bmi.value in 18.5..24.9) {
                    Text(
                        text = "NORMAL",
                        color = Color(0xFF028B7F),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (bmi.value in 25.0..29.9) {
                    Text(
                        text = "OVER-WEIGHT",
                        color = Color(0xFFDDC918),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (bmi.value in 30.0..34.9) {
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
        Button(
            onClick = {
                navController.navigate("caloriemgt");
                Toast.makeText(
                    context, "BMI Update Successful ",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "UPDATE BMI", color = Color.White, fontSize = 20.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.bmi_chart),
            contentDescription = "BMI Scale",
            modifier = Modifier.size(width = 400.dp, height = 300.dp),
        )
    }
}