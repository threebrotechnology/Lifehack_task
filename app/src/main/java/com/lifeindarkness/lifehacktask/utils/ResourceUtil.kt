package com.lifeindarkness.lifehacktask.utils

import android.content.Context
import android.util.Log
import com.lifeindarkness.lifehacktask.data.Company
import com.lifeindarkness.lifehacktask.db.AppDatabase
import dagger.Module
import javax.inject.Inject

@Module
class ResourceUtil @Inject constructor(
    context: Context
) {

    val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }

    val apiService: ApiService by lazy {
        ApiModule().apiService()
    }

    suspend fun getCompanyInfo(id: Int): Company? {
        var result: Company? = appDatabase.companyDAO().getById(id)
        if (result != null) {
            return result
        }
        try {
            result = apiService.companyInfo(id).firstOrNull()
            if (result != null) {
                return result
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Error", e.message ?: "")
        }


        return result
    }
}