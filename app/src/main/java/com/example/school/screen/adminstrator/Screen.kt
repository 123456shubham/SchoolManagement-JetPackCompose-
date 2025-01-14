package com.example.school.screen.adminstrator

sealed class Screen (val screen: String){
    data object home:Screen("home")
    data object addTeacher:Screen("addTeacher")
    data object addStudent:Screen("addStudent")
    data object profile:Screen("profile")
    data object classWise:Screen("class")
}