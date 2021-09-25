package com.example.composeamiibo.api

import com.example.composeamiibo.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    // Define the interceptor, add authentication headers
//    Interceptor interceptor = new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
//            return chain.proceed(newRequest);
//        }
//    };
//
//    // Add the interceptor to OkHttpClient
//
//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//    builder.interceptors().add(interceptor);
//    OkHttpClient client = builder.build();

//    // Set the custom client when building adapter
//    Retrofit retrofit = new Retrofit.Builder()
//    .baseUrl("https://api.github.com")
//    .addConverterFactory(GsonConverterFactory.create())
//    .client(client)
//    .build();



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