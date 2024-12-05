package com.elmaddinasger.databaseexample.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryTable")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val countryId: Long,
    val countryName: String,
    val countryCode: Short,
)
