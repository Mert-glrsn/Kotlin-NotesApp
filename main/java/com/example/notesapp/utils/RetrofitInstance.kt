package com.example.notesapp.utils

import com.example.notesapp.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val api:ApiInterface by lazy {
        Retrofit.Builder().baseUrl(Utils.Base).addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
    }
}