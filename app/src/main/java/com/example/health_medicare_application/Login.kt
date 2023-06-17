package com.example.health_medicare_application

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(context: Context, navController: NavController,databaseHelper: UserDatabaseHelper)
{
    Scaffold(
        topBar = { TopBar("LOGIN") },
        content = {pad -> LoginFill(pad,context,navController,databaseHelper) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFill(h: PaddingValues,context: Context, navController: NavController,databaseHelper: UserDatabaseHelper) {
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
        val logemail = remember { mutableStateOf(TextFieldValue("")) }
        val logpw = remember { mutableStateOf(TextFieldValue("")) }
        val pwvisible = remember { mutableStateOf(false) }
        Image(
            painter = painterResource(id = R.drawable.naren_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(175.dp).padding(top = 25.dp)
        )
        Text(
            text = "Logo Image",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        TextField(
            value = logemail.value,
            onValueChange = {
                logemail.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Email ID") },
            modifier = Modifier.fillMaxWidth().border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
        TextField(
            value = logpw.value,
            onValueChange = {
                logpw.value = it
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Password") },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            visualTransformation = if (pwvisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (pwvisible.value) {
                    IconButton(onClick = { pwvisible.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "show_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { pwvisible.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )
        Button(
            onClick = {
                if (logemail.value.text.isNotEmpty() && logpw.value.text.isNotEmpty()) {
                    val user = databaseHelper.getUserByUseremail(logemail.value.text)
                    if (user != null && user.password == logpw.value.text) {
                        navController.navigate("dashboard/${logemail.value.text}");
                        Toast.makeText(
                            context, "Log In Successful ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context, "Invalid User Credentials ",
                        Toast.LENGTH_SHORT
                    ).show()
                }



            },
            modifier=Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "LOG IN / SIGN IN", color = Color.White, fontSize = 20.sp
            )
        }
        Text(
            text = "Forgot Password ?", color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                navController.navigate("forgotpw")
            },
            modifier=Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "RESET PASSWORD", color = Color.White, fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "New User ? Register yourself", color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                navController.navigate("reg")
            },
            modifier=Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text(
                text = "REGISTER / SIGN UP", color = Color.White, fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}