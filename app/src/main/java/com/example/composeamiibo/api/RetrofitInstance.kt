package com.example.composeamiibo.api

import com.example.composeamiibo.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AmiiboApi by lazy{
        retrofit.create(AmiiboApi::class.java)
    }
}