package com.example.composeamiibo.repository


import com.example.composeamiibo.api.RetrofitInstance
import com.example.composeamiibo.model.AmiiboDatabase
import retrofit2.Response

class Repository {
    suspend fun getAmiibo(): Response<AmiiboDatabase> {
        return RetrofitInstance.api.getAmiibo()
    }
}