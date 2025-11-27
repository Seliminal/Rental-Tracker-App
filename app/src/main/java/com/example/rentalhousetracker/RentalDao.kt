package com.example.rentalhousetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//SQL lite database p2

//defining data access object methods so the app can interact with the data in the rental card table
//full crud methods
@Dao
interface RentalDao {
    @Query("SELECT * FROM rentals")
    fun getAll(): List<Rental>

    @Insert
    fun insert(rental: Rental): Long

    @Update
    fun update(rental: Rental)

    @Delete
    fun delete(rental: Rental)
}
