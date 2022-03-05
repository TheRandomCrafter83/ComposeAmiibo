package com.example.composeamiibo

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myResponse: MutableLiveData<Response<Root>> = MutableLiveData()

    lateinit var selectedAmiibo: Amiibo

    fun getAmiibo(){
        viewModelScope.launch {
            val response: Response<Root> = repository.getAmiibo()
            myResponse.value = response
        }
    }

    fun showViewAmiibo(context: Context, amiibo: Amiibo) {
        this.selectedAmiibo = amiibo
        val intent = Intent(context, AmiiboViewer::class.java)
        intent.action = Intent.ACTION_VIEW
        context.startActivity(intent)
    }
}