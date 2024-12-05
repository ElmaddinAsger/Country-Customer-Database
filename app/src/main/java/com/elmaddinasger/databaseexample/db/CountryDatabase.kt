package com.elmaddinasger.databaseexample.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class, CustomerEntity::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun customerDao(): CustomerDao

    companion object {

        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getDatabase(context: Context):CountryDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,"CountryDatabase"
                ).build()
                INSTANCE =instance
                instance
            }
        }
    }
}