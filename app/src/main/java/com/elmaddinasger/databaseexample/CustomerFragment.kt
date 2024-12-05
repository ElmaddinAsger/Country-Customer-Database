package com.elmaddinasger.databaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.elmaddinasger.databaseexample.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {
    private lateinit var binding : FragmentCustomerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddCustomer.setOnClickListener{
            findNavController().navigate(R.id.action_customerFragment_to_addCustomerFragment)
        }
        binding.btnAddCountry.setOnClickListener {
            findNavController().navigate(R.id.action_customerFragment_to_addCountryFragment)
        }
    }

}