package com.elmaddinasger.databaseexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elmaddinasger.databaseexample.db.CountryEntity

class CountryViewModel(application: Application): AndroidViewModel(application) {
    private val countryRepository = CountryRepository(application)
    val countriesDbList: LiveData<List<CountryEntity>> = countryRepository.getCountries()
    val countries: LiveData<List<String>> = countriesDbList.map {
        it.map {
            it.countryName
        }
    }
}