package com.elmaddinasger.databaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elmaddinasger.databaseexample.databinding.FragmentAddCountryBinding
import com.elmaddinasger.databaseexample.db.CountryDao
import com.elmaddinasger.databaseexample.db.CountryDatabase
import com.elmaddinasger.databaseexample.db.CountryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCountryFragment : Fragment() {
    private lateinit var binding: FragmentAddCountryBinding
    private lateinit var countryDatabase: CountryDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCountryBinding.inflate(inflater,container,false)
        countryDatabase = CountryDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddCountry.setOnClickListener {
            addCountry()
        }

    }

    private fun addCountry() {
        val countryName = binding.edtAddCountry.text.toString()
        val countryCode = binding.edtAddCountryCode.text.toString()
        if (countryName.isNotNullBlankOrEmpty() && countryCode.isNotNullBlankOrEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                val entity = CountryEntity(0, countryName, countryCode.toShort())
                countryDatabase.countryDao().insert(entity)
                withContext(Dispatchers.Main){
                    binding.edtAddCountry.setText("")
                    binding.edtAddCountryCode.setText("")
                    Toast.makeText(requireContext(),"Country Saved",Toast.LENGTH_SHORT).show()
                }
            }

        }else {
            Toast.makeText(requireContext(),"Add All information", Toast.LENGTH_SHORT).show()
        }
    }


}