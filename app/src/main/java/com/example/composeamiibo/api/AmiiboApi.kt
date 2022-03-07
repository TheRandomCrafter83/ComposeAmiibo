package com.example.composeamiibo.api

import com.example.composeamiibo.model.AmiiboDatabase
import retrofit2.Response
import retrofit2.http.GET

interface AmiiboApi {

    //@Headers("api-key: " + "apikey")
    @GET("api/amiibo/?showusage")
    suspend fun getAmiibo(): Response<AmiiboDatabase>

}