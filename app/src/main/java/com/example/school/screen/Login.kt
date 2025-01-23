package com.example.school.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.saveable.Saver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.saveable.*
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.school.R
import com.example.school.model.LoginRequest
import com.example.school.model.LoginResponse
import com.example.school.screen.adminstrator.HomeScreen
import com.example.school.viewModel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.arramton.bitewe.utils.LoginManager
import com.example.school.sealed.ApiResponse

import kotlin.math.log


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Login(navController: NavController,loginManager: LoginManager) {


    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Select an Option") }
    val options = listOf("Teacher", "Other Staff", "Parents", "Driver")
    // State variables
    var mobileNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var context= LocalContext.current

    val loginViewModel: LoginViewModel = hiltViewModel()
    val loginState by loginViewModel.loginObservable.collectAsState()

// Assuming loginState is of type LoginState (as we discussed earlier)

    LaunchedEffect(loginState) {
        when {
            loginState.success == true -> {
                // Login successful
                Toast.makeText(
                    context,
                    "Login successful: ${loginState.message}",
                    Toast.LENGTH_SHORT
                ).show()

                // Store the token using your loginManager
                loginManager.setToken(loginState.token ?: "")

                // Navigate to the home screen
                navController.navigate("home") {
                    // Clear the back stack to prevent returning to the login screen
                    popUpTo("login") { inclusive = true }
                }
            }
            loginState.success == false -> {
                // Login failed
                Toast.makeText(
                    context,
                    "Login failed: ${loginState.message ?: "Unknown error"}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                // Handle unexpected or null cases
                Toast.makeText(
                    context,
                    "Unexpected state encountered.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//    LaunchedEffect(loginState) {
//        when (val state = loginState) {
//
//
//            if (state.success==true){
//
//            }else{
//
//            }
////            is ApiResponse.Loading -> {
////                // Optionally, show a loading indicator (e.g., a ProgressBar)
////                // You could trigger a ProgressBar or loading spinner here.
////            }
////            is ApiResponse.Success -> {
////                // Show success toast message
////                Toast.makeText(
////                    context,
////                    " ${state.data?.message}",
////                    Toast.LENGTH_SHORT
////                ).show()
////
////                // Store the token using your loginManager
////                loginManager.setToken(state.data?.token.toString())
////
////                // Navigate to home screen
////                navController.navigate("home") {
////                    // Clear the back stack so the user cannot return to the login screen
////                    popUpTo("login") { inclusive = true }
////                }
////            }
////            is ApiResponse.Error -> {
////                // Show error toast or alert message
////
////                Toast.makeText(
////                    context,
////                    "Error: ${state.errorMessage}",
////                    Toast.LENGTH_SHORT
////                ).show()
////            }
////
////            else -> {
////                // Optionally handle any unexpected state or 'null' cases here
////                // This will catch any unexpected state (such as if loginState is null)
////            }
//        }
//    }






    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(colorResource(id = R.color.red))
    ) {
        // Top Content
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Top Image Section
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f, fill = true) // Occupy remaining space above the bottom section
                    .padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.school_logo),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            // Bottom Section
            Column(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                    )
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween // Space between items
            ) {

                Divider(modifier = Modifier
                    .width(50.dp)
                    .background(colorResource(id = R.color.red))
                    .align(Alignment.CenterHorizontally))
                Text(
                    text = "Welcome",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )





                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(
                        Color.White
                    )) {
                    // Trigger: OutlinedTextField for Dropdown
                    OutlinedTextField(
                        value = selectedOptionText,
                        onValueChange = {selectedOptionText==it},
                        label = { Text("Login Type") },
                        readOnly = true, // Prevent manual text editing
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                // Capture TextField size for dropdown width
//                    textFieldSize = coordinates.size.toSize()
                            }
                            .clickable { expanded = true }, // Open dropdown on click
                        trailingIcon = {
                            Icon(
                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable { expanded = !expanded }
                            )
                        }
                    )

                    // Dropdown Menu
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }, // Close when clicking outside
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOptionText = option // Update selected option
                                    expanded = false // Close dropdown
                                }
                            )
                        }
                    }
                }



                // Mobile Number Input
                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = { mobileNumber = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    label = { Text("Enter Mobile Number") },
                    placeholder = { Text("Enter Mobile Number") }
                )

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    label = { Text("Enter Password") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = null
                        )
                    },
                    placeholder = { Text("Enter Password") }
                )

                // Login Button
                Button(
                    onClick = {
                       loginApi(context,loginType =selectedOptionText , mobile = mobileNumber, password =password, loginViewModel =loginViewModel  )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.red)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}





fun loginApi(context:Context,loginType:String, mobile:String, password:String,loginViewModel: LoginViewModel){



    if (loginType.isEmpty()){
        Toast.makeText(context,"Select Login Type",Toast.LENGTH_SHORT).show()
        return
    }

    if (loginType.equals("Select an Option")){
        Toast.makeText(context,"Select Login Type",Toast.LENGTH_SHORT).show()
        return
    }


    if (mobile.isEmpty()){
        Toast.makeText(context,"Enter Mobile Number",Toast.LENGTH_SHORT).show()
        return

    }

    if (password.isEmpty()){
        Toast.makeText(context,"Enter Password",Toast.LENGTH_SHORT).show()
        return
    }


    loginViewModel.loginObserver(LoginRequest(loginType,password,mobile))

}

