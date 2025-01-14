package com.example.school.screen.common

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.arramton.bitewe.utils.LoginManager
import com.example.school.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,loginManager: LoginManager){


    LaunchedEffect(Unit) {
        delay(3000) // 3-second delay
        if (loginManager.getToken().isNullOrEmpty()) {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
    Box (
        contentAlignment = Alignment.Center, // Centers the Image inside the Box
        modifier = Modifier
        .background(Color.Red)
        .fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.school_logo

        ), contentDescription =null )

    }
}