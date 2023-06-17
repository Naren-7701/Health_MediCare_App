package com.example.health_medicare_application


import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ContactEmergency
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.health_medicare_application.ui.theme.Health_MediCare_ApplicationTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = UserDatabaseHelper(this)
        setContent {
            Health_MediCare_ApplicationTheme {
                // A surface container using the 'background' color from the theme
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
                            //permission accepted Do Something
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
            RegistrationPage(context,navController = navController,databaseReference,databaseHelper)
        }
        composable("login") {
            LoginPage(context,navController = navController,databaseHelper)
        }
        composable("forgotpw") {
            ForgotPasswordPage(context,navController = navController,databaseReference,databaseHelper)
        }
        composable("dashboard/{email}") {
                backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            DashboardPage(context,navController =navController,databaseHelper,email=email)
        }
        composable("caloriemgt") {
            CaloriePage(context,navController =navController)
        }
        composable("docsearch") {
            //UserDetailPage(navController =navController)
        }
        composable("medsearch") {
            //UserDetailPage(navController =navController)
        }
        composable("article") {
            HealthArticlePage(navController =navController)
        }
        composable("emergency") {
            EmergencyContactPage(navController =navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(abc:String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF673AB7)),
        title = {
            Text(
                "" + abc, color = Color(0xFFFFFFFF),
                fontSize = 25.sp, fontWeight = FontWeight.Bold
            )
        })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopDashboardBar(abc:String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = White),
        title = {
            Text(
                "" + abc, color = Color(0xFF673AB7),
                fontSize = 25.sp, fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApplicationBar(abc:String,back:String,navController: NavController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = White),
        title = {
            Text(
                "" + abc, color = Color(0xFF673AB7),
                fontSize = 25.sp, fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(""+back) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        })
}
@Composable
fun BottomBar(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)
        .background(Color(0xFF673AB7)),
        horizontalArrangement = Arrangement.SpaceAround)
    {
        Button(
            onClick = {navController.navigate("caloriemgt")},
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
        ) {
            Icon(imageVector = Icons.Outlined.PieChartOutline, contentDescription = "Calorie", modifier = Modifier.size(24.dp))
        }
        Button(
            onClick = {navController.navigate("article")},
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
        ) {
            Icon(imageVector = Icons.Filled.Article, contentDescription = "Article",modifier = Modifier.size(24.dp))
        }
        Button(
            onClick = {navController.navigate("docsearch")},
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Doctor Search",modifier = Modifier.size(24.dp))
        }
        Button(
            onClick = {navController.navigate("medsearch")},
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
        ) {
            Icon(imageVector = Icons.Filled.MedicalServices, contentDescription = "Medicine Search",modifier = Modifier.size(24.dp))
        }
        Button(
            onClick = {navController.navigate("emergency")},
            colors = ButtonDefaults.buttonColors(Color(0xFF673AB7)),
        ) {
            Icon(imageVector = Icons.Filled.ContactEmergency, contentDescription = "Emergency Dial",modifier = Modifier.size(24.dp) )
        }
    }
}