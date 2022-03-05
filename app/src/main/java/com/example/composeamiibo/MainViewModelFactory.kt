package com.example.composeamiibo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeamiibo.repository.Repository

class MainViewModelFactory(private val repository: Repository, val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}