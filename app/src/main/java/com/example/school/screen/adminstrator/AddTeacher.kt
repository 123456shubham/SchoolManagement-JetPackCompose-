package com.example.school.screen.adminstrator

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.school.R
import com.example.school.model.addTeacher.AddTeacherRequest
import com.example.school.sealed.ApiResponse
import com.example.school.viewModel.LoginViewModel
import com.example.school.viewModel.StaffViewModel

@Preview
@Composable
fun AddTeacher() {
    val genderList = listOf("Male", "Female", "Other")
    var gender by rememberSaveable { mutableStateOf(genderList[0]) }

    var name by rememberSaveable { mutableStateOf("") }
    var teacherMobile by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var dob by rememberSaveable { mutableStateOf("") }
    var classSelected by rememberSaveable { mutableStateOf("Class Teacher") }
    var subjectSelected by rememberSaveable { mutableStateOf("Subject") }
    var isClassDropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var isSubjectDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) } // Fix here

    val classOptions = listOf("Class 1st", "Class 2nd", "Class 3rd", "Class 4th", "Class 5th")
    val subjectOptions = listOf("Hindi", "English", "Math", "Science", "Social Studies")

    val context= LocalContext.current

    val staffViewModel: StaffViewModel = hiltViewModel()
    val staffState by staffViewModel.addTeacherObservable.collectAsState(initial = null)


    LaunchedEffect(staffState) {
        when(val state=staffState){
            is ApiResponse.Success->{

                Toast.makeText(
                    context,
                    " ${state.data?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is ApiResponse.Error -> {Toast.makeText(
                context,
                " ${state.errorMessage}",
                Toast.LENGTH_SHORT
            ).show()}
            is ApiResponse.Loading -> {}
            null -> {

            }
        }

    }
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.red))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(colorResource(id = R.color.white))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clickable { showBottomSheet = true }
                        .size(130.dp)
                ) {
                    if (selectedImageUri == null) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Default Image",
                            modifier = Modifier
                                .matchParentSize()
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImageUri),
                            contentDescription = "Selected Image",
                            contentScale = ContentScale.Crop, // Ensures image is cropped correctly
                            modifier = Modifier
                                .matchParentSize()
                                .clip(CircleShape)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.BottomEnd)
                            .padding(4.dp)
                    )
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Class Dropdown
                OutlinedTextField(
                    value = classSelected,
                    onValueChange = {classSelected=it},
                    readOnly = true,
                    label = { Text("Class Teacher") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (isClassDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                            contentDescription = "Class Dropdown",
                            modifier = Modifier.clickable { isClassDropdownExpanded = !isClassDropdownExpanded }
                        )
                    },
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(end = 5.dp)
                        .clickable { isClassDropdownExpanded = true }
                )
                DropdownMenu(
                    expanded = isClassDropdownExpanded,
                    onDismissRequest = { isClassDropdownExpanded = false }
                ) {
                    classOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                classSelected = option
                                isClassDropdownExpanded = false
                            }
                        )
                    }
                }

                // Subject Dropdown
                OutlinedTextField(
                    value = subjectSelected,
                    onValueChange = {subjectSelected=it},
                    readOnly = true,
                    label = { Text("Subject") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (isSubjectDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                            contentDescription = "Subject Dropdown",
                            modifier = Modifier.clickable { isSubjectDropdownExpanded = !isSubjectDropdownExpanded }
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp)
                        .clickable { isSubjectDropdownExpanded = true }
                )
                DropdownMenu(
                    expanded = isSubjectDropdownExpanded,
                    onDismissRequest = { isSubjectDropdownExpanded = false }
                ) {
                    subjectOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                subjectSelected = option
                                isSubjectDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = teacherMobile,
                onValueChange = { teacherMobile = it },
                label = { Text("Enter Mobile") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            OutlinedTextField(
                value = dob,
                onValueChange = { dob = formatDate(it) },
                label = { Text("Date of Birth (dd/mm/yyyy)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Select Gender",
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )

            Row {
                genderList.forEach { option ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .selectable(
                                selected = (gender == option),
                                onClick = { gender = option }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = (gender == option), onClick = null)
                        Text(text = option, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }

            Button(
                onClick = { selectedImageUri?.let {
                    addTeacherApi(context, staffViewModel,teacherName = name, classTeacher = classSelected, subjectName = subjectSelected,teacherMobile,password, dob, gender)
                } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red))
            ) {
                Text("Add Teacher")
            }
        }
    }

    // Show bottom sheet for picking an image
    if (showBottomSheet) {
        ImagePickerBottomSheet(
            onDismiss = { showBottomSheet = false },
            onImageSelected = { uri ->
                selectedImageUri = uri // Update the selected image URI
                showBottomSheet = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerBottomSheet(onDismiss: () -> Unit, onImageSelected: (Uri) -> Unit) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                onImageSelected(uri) // Pass the selected URI back to the parent composable
            }
        }
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),

                onClick = {
                    imagePickerLauncher.launch("image/*") // Trigger the gallery picker
                }
            ) {
                Text("Pick from Gallery")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    }
}

fun addTeacherApi(context: Context,stateViewModel:StaffViewModel,teacherName:String,classTeacher:String,subjectName:String,mobile:String,password:String,dob:String,gender:String){

    if (teacherName.trim().isEmpty()){
        Toast.makeText(context,"Enter Teacher Name",Toast.LENGTH_SHORT).show()
        return
    }

    if (classTeacher.trim().isEmpty()) {
        Toast.makeText(context, "Select Class Teacher", Toast.LENGTH_SHORT).show()
        return
    }

    if (subjectName.trim().isEmpty()) {
        Toast.makeText(context, "Select Subject Name", Toast.LENGTH_SHORT).show()
        return
    }

    if (subjectName.trim().isEmpty()) {
        Toast.makeText(context, "Select Subject Name", Toast.LENGTH_SHORT).show()
        return
    }

    if (mobile.trim().isEmpty()){
        Toast.makeText(context, "Select Subject Name", Toast.LENGTH_SHORT).show()
        return
    }


    if (password.trim().isEmpty()){
        Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show()
        return
    }

    if (dob.trim().isEmpty()){
        Toast.makeText(context, "Enter DOB", Toast.LENGTH_SHORT).show()
        return
    }

    if (gender.trim().isEmpty()){
        Toast.makeText(context, "Enter Gender", Toast.LENGTH_SHORT).show()
        return
    }

    val addTeacherRequest= AddTeacherRequest(classTeacher,dob,gender,teacherName,password, phone = mobile,subjectName,"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fphto%3Fpage%3D4&psig=AOvVaw397cObxtFY-kvf4Ky1fVdr&ust=1736931833697000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCID0tLrt9IoDFQAAAAAdAAAAABAE")
    stateViewModel.addTeacherObserver(addTeacherRequest)



}
fun formatDate(input: String): String {
    // Remove non-digit characters (like spaces, dashes, etc.)
    val cleanedInput = input.replace(Regex("[^\\d]"), "")

    // Ensure the cleaned input is valid for processing
    if (cleanedInput.length > 8) return input // Return as-is if too long

    // Extract day, month, and year while padding single digits
    val day = cleanedInput.take(2).padStart(2, '0') // First 1-2 digits padded to 2
    val month = cleanedInput.drop(2).take(2).padStart(2, '0') // Next 1-2 digits padded to 2
    val year = cleanedInput.drop(4).take(4) // Remaining digits (up to 4 for year)

    return when {
        year.isNotEmpty() && month.isNotEmpty() && day.isNotEmpty() -> "$day/$month/$year"
        month.isNotEmpty() && day.isNotEmpty() -> "$day/$month"
        day.isNotEmpty() -> day
        else -> input // Fallback for invalid input
    }
}

