package com.elmaddinasger.databaseexample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {
    @Query("SELECT * from CustomerTable")
    fun getAll (): List<CustomerEntity>
    @Insert
    fun insert(customer: CustomerEntity): Long
}