package com.example.composeamiibo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeamiibo.model.Amiibo
import com.example.composeamiibo.model.Root
import com.example.composeamiibo.repository.Repository
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myResponse: MutableLiveData<Response<Root>> = MutableLiveData()
    fun getAmiibo(){
        viewModelScope.launch {
            val response: Response<Root> = repository.getAmiibo()
            myResponse.value = response
        }
    }

    fun showViewAmiibo(context: Context, amiibo: Amiibo) {
        val bundle = Bundle()
        val encAmiibo: String = Json.encodeToString(amiibo)
        bundle.putString(SEL_ITEM_KEY, encAmiibo)
        val intent = Intent(context, AmiiboViewer::class.java)
        intent.action = Intent.ACTION_VIEW

        intent.putExtra(SEL_ITEM_KEY, bundle)
        context.startActivity(intent)
    }
}