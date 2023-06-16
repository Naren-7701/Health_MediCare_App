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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
fun RegistrationPage(context: Context, navController: NavController,databaseReference: DatabaseReference)
{
    Scaffold(
        topBar = { TopBar("REGISTRATION") },
        content = {pad -> RegisterFill(pad,context,navController,databaseReference) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFill(h: PaddingValues,context: Context,navController: NavController,databaseReference: DatabaseReference) {
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
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val mobile = remember { mutableStateOf(TextFieldValue("")) }
        val maxmobileLength = 10
        val regpw = remember { mutableStateOf(TextFieldValue("")) }
        val passwordVisible1 = remember { mutableStateOf(false) }
        Image(
            painter = painterResource(id = R.drawable.naren_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 30.dp)
        )
        TextField(
            value = regemail.value,
            onValueChange = {
                regemail.value = it;
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            label = { Text("Enter Email ID") },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color(0xFF673AB7))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
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
                        databaseReference.child(name.value.text + "").setValue(userObj)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            context, "Registration UnSuccessful ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                });
                navController.navigate("detail")
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