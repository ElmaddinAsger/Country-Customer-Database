package com.elmaddinasger.databaseexample

import android.app.Application
import androidx.lifecycle.LiveData
import com.elmaddinasger.databaseexample.db.CountryDao
import com.elmaddinasger.databaseexample.db.CountryDatabase
import com.elmaddinasger.databaseexample.db.CountryEntity

class CountryRepository(application: Application) {

    private val countryDao: CountryDao = CountryDatabase.getDatabase(application).countryDao()

    fun getCountries() : LiveData<List<CountryEntity>>{
        return countryDao.getAll()
    }
}