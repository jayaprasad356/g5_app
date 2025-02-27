package com.gmwapp.slv_g5.ProfileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmwapp.slv_g5.activities.MainActivity
import com.gmwapp.slv_g5.databinding.FragmentMyBankBinding
import com.gmwapp.slv_g5.helper.Session

class MyBankFragment : Fragment() {


    lateinit var binding: FragmentMyBankBinding
    lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyBankBinding.inflate(inflater, container, false)
        session = Session(requireContext())

        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        binding.ibBack.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().onBackPressed()
        })

        return binding.root
    }
}