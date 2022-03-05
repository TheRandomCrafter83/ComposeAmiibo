package com.example.composeamiibo

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository, application: Application): AndroidViewModel(
    application
) {

    val myResponse: MutableLiveData<Response<Root>> = MutableLiveData()
    private val app:Application = getApplication()
    lateinit var selectedAmiibo: Amiibo

    fun getAmiibo(){
        viewModelScope.launch {
            val response: Response<Root> = repository.getAmiibo()
            myResponse.value = response
        }
    }

    fun recyclerViewSelectedCardClick(amiibo: Amiibo) {
        this.selectedAmiibo = amiibo
        val intent = Intent(this.getApplication(), AmiiboViewer::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        app.startActivity(intent)
    }
}