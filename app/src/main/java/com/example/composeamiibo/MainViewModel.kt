package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myResponse: MutableLiveData<Response<Root>> = MutableLiveData()
    fun getAmiibo(){
        viewModelScope.launch {
            val response: Response<Root> = repository.getAmiibo()
            myResponse.value = response
        }
    }
}