package com.example.school.screen.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arramton.bitewe.utils.LoginManager
import com.example.school.screen.Login
import com.example.school.screen.adminstrator.AdministrationHomePage

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val context= LocalContext.current
    val loginManager = LoginManager(context)

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController,loginManager) }
        composable("login") { Login(navController,loginManager) }
        composable("home") { AdministrationHomePage() }
    }
}