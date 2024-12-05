package com.elmaddinasger.databaseexample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryTable")
    fun getAll(): LiveData<List<CountryEntity>>

    @Query("Select countryId from countrytable where countryName==:countryName")
    fun getId(countryName: String): Long
    @Insert
    fun insert(country: CountryEntity) : Long
}