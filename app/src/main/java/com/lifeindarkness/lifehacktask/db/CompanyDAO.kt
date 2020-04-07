package com.lifeindarkness.lifehacktask.db

import androidx.room.*
import com.lifeindarkness.lifehacktask.data.Company

@Dao
interface CompanyDAO {

    @Query("SELECT * FROM companies")
    suspend fun getAll(): List<Company>

    @Query("SELECT * FROM companies WHERE id LIKE :eventId LIMIT 1")
    suspend fun getById(eventId: Int): Company?

    @Query("DELETE FROM companies")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Company)

    @Delete
    suspend fun delete(data: Company)
}