package com.lifeindarkness.lifehacktask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lifeindarkness.lifehacktask.Constants
import com.lifeindarkness.lifehacktask.data.Company
import dagger.Module

@Database(
    entities = [Company::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            if (instance != null) {
                return instance!!
            }

            synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, Constants.mainAppDatabaseName
                ).build()

                return instance!!
            }
        }
    }

    abstract fun companyDAO(): CompanyDAO
}