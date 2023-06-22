package com.example.health_medicare_application.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Activityscreen = Modifier.fillMaxWidth().fillMaxSize().background(color = Color.White).padding(25.dp)
val fillmaxwid = Modifier.fillMaxWidth()
val wid150 = Modifier.width(150.dp)
val wid220 = Modifier.width(220.dp)
val horzcenter = Alignment.CenterHorizontally
val horzspacear = Arrangement.SpaceAround
val horzstart = Arrangement.Start
val vertspace=Arrangement.SpaceEvenly

val boxes= Modifier.fillMaxWidth().border(BorderStroke(2.dp, Color(0xFF673AB7)))

val rcshape= RoundedCornerShape(5.dp)
val iconsize = Modifier.size(35.dp)
val icon = Modifier.size(30.dp)

val reglogbuttxtcol = Color.White
val subtxtcol = Color.DarkGray

val reglogbut = 20.sp
val subtxtsize = 16.sp
val fnt24 = 24.sp
val fnt23 = 23.sp
val fnt21 = 21.sp
val fnt20 = 20.sp
val fnt19 = 19.sp
val fnt18 = 18.sp

val txtbold = FontWeight.Bold
var txtcenter = TextAlign.Center

val mobkeypad = KeyboardOptions(keyboardType = KeyboardType.Phone)