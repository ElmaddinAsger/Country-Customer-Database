package com.elmaddinasger.databaseexample.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CustomerTable",
    foreignKeys =[
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["countryId"],
            childColumns = ["countryId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val surname: String,
    val countryId: Long
)
