package com.example.rentalhousetracker

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//SQL lite database setup p1

//defining data entity "rental" and table rows for each rental
@Parcelize
@Entity(tableName = "rentals")
data class Rental(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val location: String,
    val price: String,
    val imageUrl: String = ""
) : Parcelable
