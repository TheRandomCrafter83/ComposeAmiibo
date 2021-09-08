package com.example.composeamiibo.repository


import android.provider.DocumentsContract
import com.example.composeamiibo.api.RetrofitInstance
import com.example.composeamiibo.model.Root
import retrofit2.Response

class Repository {
    suspend fun getAmiibo(): Response<Root> {
        return RetrofitInstance.api.getAmiibo()
    }
}