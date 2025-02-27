package com.gmwapp.slv_g5.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gmwapp.slv_g5.R
import com.gmwapp.slv_g5.activities.MainActivity
import com.gmwapp.slv_g5.databinding.FragmentProductDetailsBinding
import com.gmwapp.slv_g5.helper.Session

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var session: Session

    private var productName: String? = null
    private var productPrice: String? = null
    private var productImage: String? = null
    private var productDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        session = Session(requireContext())

        if (arguments != null) {
            productName = requireArguments().getString("product_name")
            productPrice = requireArguments().getString("product_price")
            productImage = requireArguments().getString("product_image")
            productDescription = requireArguments().getString("product_description")

            Log.d("ProductDetailsFragment", "Received Product Name: $productName")
            Log.d("ProductDetailsFragment", "Received Product Price: $productPrice")
        }

        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val billFragment = BillFragment()

        // Hide bottom navigation
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        // Back button functionality
        binding.ibBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.tvProductName.setText(productName)
        binding.tvPrice.setText(productPrice)
        binding.tvDescription.setText(productDescription)

        // Load image using Glide
        Glide.with(requireContext())
            .load(productImage)
            .placeholder(R.drawable.image_icon)
            .into(binding.ivProductImage)

        binding.llWaiting.setVisibility(View.VISIBLE)
        binding.frame.setVisibility(View.GONE)
        Handler().postDelayed({
            binding.llWaiting.setVisibility(View.GONE)
            binding.frame.setVisibility(View.VISIBLE)
        }, 2000)

        binding.btBuyNow.setOnClickListener({
            // Replace current fragment with billFragment
            transaction.replace(R.id.Container, billFragment)
            transaction.addToBackStack(null) // Optional: Add to backstack to allow going back
            transaction.commit()
        })

        return binding.root
    }
}
