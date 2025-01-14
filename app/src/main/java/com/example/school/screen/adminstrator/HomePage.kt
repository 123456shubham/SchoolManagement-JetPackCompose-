package com.example.school.screen.administrator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.school.screen.Login
import com.example.school.screen.adminstrator.HomeScreen

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // Create NavController instance

    NavHost(
        navController = navController,
        startDestination = "login_screen" // Define a valid startDestination as a string
    ) {
        // Login screen route
        composable("login_screen") {
//            Login {
//                navController.navigate("home_screen") // Navigate to HomeScreen
//            }
        }

        // Home screen route
        composable("home_screen") {
            HomeScreen()
        }

        // AddStudent screen route (if applicable)
        composable("add_student_screen") {
//            com.example.school.screen.adminstrator.AddStudentScreen()
        }
    }
}
