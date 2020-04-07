package com.lifeindarkness.lifehacktask.utils

import com.google.gson.GsonBuilder
import com.lifeindarkness.lifehacktask.Constants
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ApiModule @Inject constructor(){

    @Singleton
    fun apiService(): ApiService {
        val httpClient = OkHttpClient.Builder()
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(ApiService::class.java)
    }
}