package com.example.health_medicare_application

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ContactEmergency
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.health_medicare_application.model.UserDatabaseHelper
import com.example.health_medicare_application.ui.theme.Health_MediCare_ApplicationTheme
import com.example.health_medicare_application.ui.theme.horzspacear
import com.example.health_medicare_application.ui.theme.purewhite
import com.example.health_medicare_application.ui.theme.purple673
import com.example.health_medicare_application.ui.theme.txtbold
import com.example.health_medicare_application.uiactivity.CaloriePage
import com.example.health_medicare_application.uiactivity.DashboardPage
import com.example.health_medicare_application.uiactivity.Doctor
import com.example.health_medicare_application.uiactivity.EmergencyContactPage
import com.example.health_medicare_application.uiactivity.ForgotPasswordPage
import com.example.health_medicare_application.uiactivity.HealthArticlePage
import com.example.health_medicare_application.uiactivity.LoginPage
import com.example.health_medicare_application.uiactivity.Medicine
import com.example.health_medicare_application.uiactivity.RegistrationPage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = UserDatabaseHelper(this)
        setContent {
            Health_MediCare_ApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val firebasedb = FirebaseDatabase.getInstance();
                    val reference = firebasedb.getReference("User");
                    val get_permission = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted ->
                        if (isGranted) {
                            // Permission accepted Do Something
                        } else {
                            // Permission not accepted show message
                        }
                    }
                    SideEffect {
                        get_permission.launch(Manifest.permission.SEND_SMS)
                    }
                    App(applicationContext,reference,databaseHelper)
                }
            }
        }
    }
}
@Composable
fun App(context:Context,databaseReference: DatabaseReference,databaseHelper: UserDatabaseHelper) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("reg") {
            RegistrationPage(context,navController,databaseReference,databaseHelper)
        }
        composable("login") {
            LoginPage(context,navController,databaseHelper)
        }
        composable("forgotpw") {
            ForgotPasswordPage(context,navController,databaseReference,databaseHelper)
        }
        composable("dashboard/{email}") {
                backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            DashboardPage(navController,databaseHelper,email)
        }
        composable("caloriemgt") {
            CaloriePage(navController)
        }
        composable("docsearch") {
            Doctor(navController,databaseHelper)
        }
        composable("medsearch") {
            Medicine(navController,databaseHelper)
        }
        composable("article") {
            HealthArticlePage(navController)
        }
        composable("emergency") {
            EmergencyContactPage(navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(abc:String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = purple673),
        title = {
            Text(
                "" + abc, color = purewhite,
                fontSize = 25.sp, fontWeight = txtbold
            )
        })
}
@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(purple673),
        horizontalArrangement = horzspacear
    )
    {
        val butcolor = ButtonDefaults.buttonColors(Color(0xFF673AB7))
        val size24 = Modifier.size(24.dp)
        Button(
            onClick = { navController.navigate("caloriemgt") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Outlined.PieChartOutline,
                contentDescription = "Calorie",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("article") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.Article,
                contentDescription = "Article",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("docsearch") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Doctor",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("medsearch") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.MedicalServices,
                contentDescription = "Medicine",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("emergency") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.ContactEmergency,
                contentDescription = "Emergency",
                modifier = size24
            )
        }
    }
}