package com.gmwapp.slv_g5.fragment

import android.graphics.Paint
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
    private var discountPercentage: String? = null
    private var mrp: String? = null

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
            discountPercentage = requireArguments().getString("discount_percentage")
            mrp = requireArguments().getString("mrp")

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
        binding.tvPrice.setText("₹" + productPrice)
        binding.tvDescription.setText(productDescription)
        binding.tvMrp.setText("MRP: ₹" + mrp)
        binding.tvDiscount.setText(discountPercentage + " discount")

        binding.tvMrp.paintFlags = binding.tvMrp.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        if(mrp?.isEmpty() == true || mrp == "") {
            binding.tvMrp.visibility = View.GONE
        }

        if(discountPercentage?.isEmpty() == true || mrp == "") {
            binding.mcDiscount.visibility = View.GONE
        }

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
        }, 1000)

        binding.btBuyNow.setOnClickListener {
            // Create instance of BillFragment
            val billFragment = BillFragment()

            // Create a Bundle to pass data
            val bundle = Bundle()
            bundle.putString("product_name", productName)
            bundle.putString("product_price", productPrice)
            bundle.putString("product_image", productImage)
            bundle.putString("product_description", productDescription)
            bundle.putString("discount_percentage", discountPercentage)
            bundle.putString("mrp", mrp)
            billFragment.arguments = bundle

            // Perform fragment transaction
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.Container, billFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        return binding.root
    }
}
