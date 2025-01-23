package com.example.school.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.school.repository.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val homePageRepository: HomePageRepository):ViewModel(){


        val postObservable=homePageRepository.post

     fun postObserver(pageNumber: String,pageSize:String,sortByASC:String?,sortByDESC: String?){
        viewModelScope.launch {

                    homePageRepository.postAPI(pageNumber,pageSize,sortByASC,sortByDESC)


        }
    }

}