package com.lifeindarkness.lifehacktask.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "companies")
data class Company(
    @PrimaryKey
    var id: Int,
    var name: String,
    var img: String,
    var description: String?,
    var lat: Float?,
    var lng: Float?,
    @SerializedName("www")
    var site: String?,
    var phone: String?
)