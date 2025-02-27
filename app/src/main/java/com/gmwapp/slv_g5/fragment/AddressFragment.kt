package com.gmwapp.slv_g5.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.gmwapp.slv_g5.R
import com.gmwapp.slv_g5.activities.MainActivity
import com.gmwapp.slv_g5.databinding.FragmentAddressBinding
import com.gmwapp.slv_g5.helper.Session
import com.google.android.material.button.MaterialButton

class AddressFragment : Fragment() {

    private lateinit var binding: FragmentAddressBinding
    private lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        session = Session(requireContext())

        // Hide bottom navigation
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        // Back button functionality
        binding.ibBack.setOnClickListener { requireActivity().onBackPressed() }

        // Access spinner using binding
        val spinner: Spinner = binding.spinnerStates

        // Load states from resources
        val statesArray = resources.getStringArray(R.array.indian_states)

        // Use requireContext() instead of "this"
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statesArray)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        return binding.root
    }
}
