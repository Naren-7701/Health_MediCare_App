package com.example.health_medicare_application.uiactivity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health_medicare_application.R
import com.example.health_medicare_application.TopBar
import com.example.health_medicare_application.model.UserDatabaseHelper
import com.example.health_medicare_application.model.UserDetailDb
import com.example.health_medicare_application.model.UserObject
import com.example.health_medicare_application.ui.theme.Activityscreen
import com.example.health_medicare_application.ui.theme.boxes
import com.example.health_medicare_application.ui.theme.fillmaxwid
import com.example.health_medicare_application.ui.theme.fnt20
import com.example.health_medicare_application.ui.theme.horzcenter
import com.example.health_medicare_application.ui.theme.mobkeypad
import com.example.health_medicare_application.ui.theme.purewhite
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.rcshape
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
        topBar = { TopBar("USER REGISTRATION") },
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
        val txtfieldcol = TextFieldDefaults.textFieldColors(containerColor = Color.White)
        Image(
            painter = painterResource(id = R.drawable.health_medicare_app),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp).padding(top=25.dp)
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
        Button(
            onClick = {
                val userObj =
                    UserObject(
                        email.value.text,
                        mobile.value.text,
                        regpw.value.text,
                        name.value.text,
                    )
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseReference.child(usrname.value + "").setValue(userObj)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            context, "Registration UnSuccessful ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
                if (name.value.text.isNotEmpty() && regpw.value.text.isNotEmpty() && mobile.value.text.isNotEmpty() && email.value.text.isNotEmpty()) {
                    val user = UserDetailDb(
                        id = null,
                        email = email.value.text,
                        mobile = mobile.value.text,
                        password = regpw.value.text,
                        name = name.value.text,
                    )
                    databaseHelper.insertUser(user)
                    navController.navigate("medusreg")
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