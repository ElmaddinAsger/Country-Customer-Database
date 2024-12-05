package com.elmaddinasger.databaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.elmaddinasger.databaseexample.databinding.FragmentAddCustomerBinding
import com.elmaddinasger.databaseexample.db.CountryDao
import com.elmaddinasger.databaseexample.db.CountryDatabase
import com.elmaddinasger.databaseexample.db.CustomerDao
import com.elmaddinasger.databaseexample.db.CustomerEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCustomerFragment : Fragment() {
    private lateinit var binding : FragmentAddCustomerBinding
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countryAdapter: ArrayAdapter<String>
    private lateinit var customerDao: CustomerDao
    private lateinit var countryDao: CountryDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCustomerBinding.inflate(inflater,container,false)
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        val db = CountryDatabase.getDatabase(requireContext())
        customerDao = db.customerDao()
        countryDao = db.countryDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryViewModel.countries.observe(viewLifecycleOwner,{ countries ->
            countryAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,countries)
            countryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spnCountry.adapter = countryAdapter
        })
        countryViewModel.countriesDbList

        binding.btnSave.setOnClickListener {
            addCustomer()
        }
    }

    fun addCustomer(){
        val customerName = binding.edtCustomerName.text.toString()
        val customerSurname = binding.edtCustomerSurname.text.toString()
        val customerCountry = binding.spnCountry.selectedItem as String
        if (customerSurname.isNotEmpty() && customerName.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                val countryId = countryDao.getId(customerCountry)
                val customerEntity = CustomerEntity(0,customerName,customerSurname, countryId)
                customerDao.insert(customerEntity)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Customer Saved.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}