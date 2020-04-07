package com.lifeindarkness.lifehacktask.utils

import com.lifeindarkness.lifehacktask.data.Company
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("test.php")
    suspend fun companyList(): List<Company>

    @GET("test.php")
    suspend fun companyInfo(@Query("id") id: Int): List<Company>
}