package com.example.school.screen.adminstrator

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.school.R
import com.example.school.sealed.ApiResponse
import com.example.school.viewModel.StaffViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddStudent(){

    val scrollState = rememberScrollState()

    val genderList= listOf("Male", "Female", "Other")
    var  gender by rememberSaveable { mutableStateOf(genderList[0]) }

    val transportList= listOf("Yes", "No")
    var  transport by rememberSaveable { mutableStateOf(transportList[0]) }


    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var rollNo by rememberSaveable { mutableStateOf("") }
    var className by rememberSaveable { mutableStateOf("") }
    var selectedOption by rememberSaveable { mutableStateOf("Option 1") }


    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText by rememberSaveable { mutableStateOf("Select Class") }
    val options = listOf("Class 1st", "Class 2nd ", "Class 3rd ", "Class 4th","Class 5th","Class 6th","Class 7th","Class 8th","Class 9th","Class 10th","Class 11th With Science(Medical)","Class 11th With Science(Non-Medical)","Class 11th With Arts","Class 11th With Commerce","Class 12th With Science(Medical)","Class 12th With Science(Non-Medical)","Class 12th With Arts","Class 12th With Commerce")

    var fatherName by rememberSaveable {
        mutableStateOf("")
    }

    var fatherMobile by rememberSaveable {
        mutableStateOf("")
    }

    var motherName by rememberSaveable {
        mutableStateOf("")
    }

    var motherPhone by rememberSaveable {
        mutableStateOf("")
    }


    var password by rememberSaveable {
        mutableStateOf("")

    }

    val context= LocalContext.current

    val staffViewModel:StaffViewModel= hiltViewModel()
    val staffState by staffViewModel.addStudentObservable.collectAsState(initial = null)
    LaunchedEffect(staffState) {
        when(val state=staffState){
            is ApiResponse.Error ->{}
            is ApiResponse.Loading -> {}
            is ApiResponse.Success -> {

            }
            null -> {

            }
        }
    }

    Box (modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.red))){
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState) // Enable vertical scrolling
            .imePadding()  // Fix for keyboard overlapping
            .padding(top = 80.dp)  // Extra bottom padding


            ) {

//            Row (modifier = Modifier.padding(top = 10.dp, start = 10.dp)){
//
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = null
//                )
//                Text(text = "Add Student", fontSize = 18.sp, fontFamily = FontFamily(Font( R.font.poppins_bold)), modifier = Modifier.padding(start = 10.dp))
//
//            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .background(Color.White)

            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(130.dp) // Match the size of the Image
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Circular Image",
                        modifier = Modifier
                            .matchParentSize() // Fills the Box
                            .clip(CircleShape)
                    )

                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.BottomEnd)
                            .padding(4.dp)
                    )
                }


                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 10.dp, end = 10.dp),
                    label = { Text("Enter Name") },
                    placeholder = { Text("Name") }
                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                            end = 10.dp
                        ), // General padding for the Row
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
                ) {
                    // Dropdown Menu
                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .weight(2f) // Divide available space proportionally
                            .padding(end = 5.dp), // Space between dropdown and text field
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .clickable { expanded = true },
                            label = { Text("Class") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = null
                                )
                            },
                            readOnly = true,
                            singleLine = true // Ensures the text does not wrap
                        )

                        // Dropdown Menu Items
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selectedOptionText = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

//                    // Age Input Field
//                    OutlinedTextField(
//                        value = age,
//                        onValueChange = {
//                            if (it.length <= 3 && it.all { char -> char.isDigit() }) { // Limit input to 3 digits and numbers only
//                                age = it
//                            }
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .weight(1f) // Divide available space proportionally
//                            .padding(start = 10.dp), // Space between text field and dropdown
//                        label = { Text("Enter Age") },
//                        placeholder = { Text("Age") },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Numeric keyboard
//                        singleLine = true // Ensures single line for age field
//                    )
                }




                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                    label = { Text("Enter Father Name") },
                    placeholder = { Text("Father Name") }
                )

                OutlinedTextField(
                    value = age,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                    label = { Text("Enter Father Mobile") },
                    placeholder = { Text("Enter Father Mobile") }
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                    label = { Text("Enter Mother Name") },
                    placeholder = { Text("Mother Name") }
                )

                OutlinedTextField(
                    value = age,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                    label = { Text("Enter Mother Mobile") },
                    placeholder = { Text("Enter Mother Mobile") }
                )

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                    label = { Text("Enter Password") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = null
                        )
                    },
                    placeholder = { Text("Enter Password") }
                )


                Text(
                    text = "Select Gender",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .fillMaxWidth()
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    genderList.forEach { option ->
                        Row(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .height(56.dp)
                                .selectable(
                                    selected = gender == option,
                                    onClick = { gender = option })
                        ) {
                            RadioButton(selected = gender == option, onClick = null)
                            Text(text = option, modifier = Modifier.padding(start = 20.dp))
                        }
                    }

                }

                Text(
                    text = "Transport",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    transportList.forEach { trans ->
                        Row(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .height(56.dp)
                                .selectable(
                                    selected = transport == trans,
                                    onClick = { transport = trans })
                        ) {
                            RadioButton(selected = transport == trans, onClick = null)
                            Text(text = trans, modifier = Modifier.padding(start = 20.dp))
                        }
                    }

                }


                // Login Button
                Button(
                    onClick = {
                        // Call API or handle login logic
//                        onGoingHomePage()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.red)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                ) {
                    Text(text = "Add Student")
                }

            }

        }

        fun registerStudentApi(context: Context,studentName:String,className:String,fatherName:String,motherName:String,fatherMobile:String,motherMobile:String,password:String,gender:String,transport:Boolean){


            if (studentName.trim().isEmpty()){
                Toast.makeText(context,"Enter Student Name",Toast.LENGTH_SHORT).show()
                return;
            }

            if (className.trim().isEmpty()){
                Toast.makeText(context,"Select Class ",Toast.LENGTH_SHORT).show()
                return
            }

            if (fatherName.trim().isEmpty()){
                Toast.makeText(context,"Enter Father Name",Toast.LENGTH_SHORT).show()
                return
            }

            if (fatherMobile.trim().isEmpty()){
                Toast.makeText(context,"Enter Father Mobile Number",Toast.LENGTH_SHORT).show()
                return
            }

            if (motherName.trim().isEmpty()){
                Toast.makeText(context,"Enter Mother Name",Toast.LENGTH_SHORT).show()
                return
            }
            if (motherMobile.trim().isEmpty()){
                Toast.makeText(context,"Enter Mother Mobile Number",Toast.LENGTH_SHORT).show()
                return
            }

            if (password.trim().isEmpty()){
                Toast.makeText(context,"Enter password",Toast.LENGTH_SHORT).show()
                return
            }

            if (gender.trim().isEmpty()){
                Toast.makeText(context,"Please Select Gender",Toast.LENGTH_SHORT).show()
                return
            }

            if (transport==false){
                Toast.makeText(context,"Please Select Transport Availability",Toast.LENGTH_SHORT).show()
                return
            }


        }
    }

}