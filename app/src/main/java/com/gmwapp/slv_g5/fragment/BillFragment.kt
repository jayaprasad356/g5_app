package com.gmwapp.slv_g5.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gmwapp.slv_g5.R
import com.gmwapp.slv_g5.activities.MainActivity
import com.gmwapp.slv_g5.databinding.FragmentBillBinding
import com.gmwapp.slv_g5.helper.Session
import com.google.android.material.button.MaterialButton

class BillFragment : Fragment() {

    private lateinit var binding: FragmentBillBinding
    private lateinit var session: Session

    var btBuyNow: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillBinding.inflate(inflater, container, false)
        session = Session(requireContext())

        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val addressFragment = AddressFragment()

        // Hide bottom navigation
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        // Back button functionality
        binding.ibBack.setOnClickListener { requireActivity().onBackPressed() }

        // Initialize RadioButtons
        binding.rdbSelectUPI.isChecked = true

        // Set listeners for RadioButtons
        binding.rdbSelectUPI.setOnClickListener { onRadioButtonClicked(binding.rdbSelectUPI) }
        binding.rdbSelectVoucher.setOnClickListener { onRadioButtonClicked(binding.rdbSelectVoucher) }

        binding.llWaiting.setVisibility(View.VISIBLE)
        binding.frame.setVisibility(View.GONE)
        Handler().postDelayed({
            binding.llWaiting.setVisibility(View.GONE)
            binding.frame.setVisibility(View.VISIBLE)
        }, 2000)

        binding.btnEditAddress.setOnClickListener({
            // Replace current fragment with billFragment
            transaction.replace(R.id.Container, addressFragment)
            transaction.addToBackStack(null) // Optional: Add to backstack to allow going back
            transaction.commit()
        })

        return binding.root
    }

    private fun onRadioButtonClicked(selectedRadioButton: RadioButton) {
        // Uncheck the other RadioButton
        if (selectedRadioButton == binding.rdbSelectUPI) {
            binding.rdbSelectVoucher.isChecked = false
        } else if (selectedRadioButton == binding.rdbSelectVoucher) {
            binding.rdbSelectUPI.isChecked = false
        }

        // Mark the selected RadioButton as checked
        selectedRadioButton.isChecked = true
    }
}