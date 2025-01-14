package com.example.school.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.school.model.addStudent.AddStudentRequest
import com.example.school.model.addTeacher.AddTeacherRequest
import com.example.school.repository.StaffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffViewModel @Inject constructor(private val staffRepository: StaffRepository):ViewModel() {
    val addTeacherObservable=staffRepository.addTeacher
    fun addTeacherObserver( addTeacherRequest: AddTeacherRequest){
        viewModelScope.launch {
            staffRepository.addTeacherApi(addTeacherRequest)
        }
    }

    val addStudentObservable=staffRepository.addStudent
    fun addStudentObserver(studentRequest: AddStudentRequest){
        viewModelScope.launch{
            staffRepository.addStudentApi(studentRequest)
        }
    }
}