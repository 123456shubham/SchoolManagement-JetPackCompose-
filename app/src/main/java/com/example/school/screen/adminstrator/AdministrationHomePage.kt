package com.example.school.screen.adminstrator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.school.R
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AdministrationHomePage(){
        val navigationController = rememberNavController()
        val coroutinesScope= rememberCoroutineScope()
        val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
        val context= LocalContext.current.applicationContext
        ModalNavigationDrawer(
            drawerState=drawerState,
            gesturesEnabled = true,
            drawerContent = {
                ModalDrawerSheet {
                    Box (modifier = Modifier
                        .background(colorResource(id = R.color.red))
                        .fillMaxWidth()
                        .height(150.dp)){
                        Text(text = "")
                    }
                    Divider()
                    NavigationDrawerItem(label = { Text(text = "Home", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="home", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screen.home.screen){
                                popUpTo(0)
                            }
                        })

                    NavigationDrawerItem(label = { Text(text = "Add Teacher", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="addTeacher", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screen.addTeacher.screen){
                                popUpTo(0)
                            }
                        })

                    NavigationDrawerItem(label = { Text(text = "Add Student", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="addStudent", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screen.addStudent.screen){
                                popUpTo(0)
                            }
                        })

                    NavigationDrawerItem(label = { Text(text = "Profile", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="profile", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screen.profile.screen){
                                popUpTo(0)
                            }
                        })


                    NavigationDrawerItem(label = { Text(text = "Class", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="class", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screen.classWise.screen){
                                popUpTo(0)
                            }
                        })

                    NavigationDrawerItem(label = { Text(text = "Logout", color = Color.Black) },
                        selected =false ,
                        icon = { Icon(imageVector = Icons.Default.Home , contentDescription ="logout", tint = Color.Black ) },
                        onClick = {
                            coroutinesScope.launch {
                                drawerState.close()
                            }
                            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        })
                }
            })

        {

            Scaffold (topBar = {
                val coroutineScope= rememberCoroutineScope()
                TopAppBar(title = { Text(text = "Adminstrator") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.red),  // Use color from colors.xml
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {

                            Icon(Icons.Rounded.Menu , contentDescription = "Menu button")

                        }
                    }
                )
            }){
                NavHost(navController = navigationController, startDestination = Screen.home.screen  ){
                    composable(Screen.home.screen){ HomeScreen() }
                    composable(Screen.addTeacher.screen){ AddTeacher() }
                    composable(Screen.addStudent.screen){ AddStudent() }
                    composable(Screen.profile.screen){ Profile() }
                    composable(Screen.classWise.screen){ ClassWise() }
                }

            }

        }
}