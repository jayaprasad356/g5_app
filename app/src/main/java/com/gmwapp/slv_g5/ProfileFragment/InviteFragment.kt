package com.gmwapp.slv_g5.ProfileFragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmwapp.slv_g5.Adapter.ReferUserAdapter
import com.gmwapp.slv_g5.activities.MainActivity
import com.gmwapp.slv_g5.databinding.FragmentInviteBinding
import com.gmwapp.slv_g5.helper.ApiConfig
import com.gmwapp.slv_g5.helper.Constant
import com.gmwapp.slv_g5.helper.Session
import com.gmwapp.slv_g5.model.ReferPlansModel
import org.json.JSONException
import org.json.JSONObject

class InviteFragment : Fragment() {

    lateinit var binding: FragmentInviteBinding
    lateinit var session: Session
    lateinit var referUserAdapter: ReferUserAdapter
    var referPlans: MutableList<ReferPlansModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInviteBinding.inflate(inflater, container, false)
        session = Session(requireContext())

        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        binding.ibBack.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().onBackPressed()
        })


        // Set click listener for the "Refer" button
        if (session != null && session.getData(Constant.REFER_CODE) != null) {
            binding.btnRefer.setOnClickListener(View.OnClickListener { v: View? ->
                val baseUrl = Constant.PLAY_STORE_URL // Replace with your actual base URL
                shareTextAndUrl(
                    """Click this link to join G-5 App ☺️
Use My Refer Code ${session.getData(Constant.REFER_CODE)} While Creating Account.""", baseUrl
                )
            })
        } else {
            val baseUrl = "https://aidiapp.in/" // Replace with your actual base URL
            shareTextAndUrl(
                """
            Click this link to join G-5 App ☺️
            Use My Refer Code ID123 While Creating Account.
            """.trimIndent(), baseUrl
            )
        }

        var referCode = session.getData(Constant.REFER_CODE)

        // Check if session and data are not null, then set the text
        if (session.getData(Constant.REFER_CODE) != null) {
            binding.btnReferText.text = session.getData(Constant.REFER_CODE)
        } else {
            binding.btnReferText.text = "123456"
        }

        binding.btnReferText.setOnClickListener(View.OnClickListener { v: View? ->
            if (session.getData(Constant.REFER_CODE) == null || session.getData(Constant.REFER_CODE).isEmpty()) {
                referCode = "123456" // Default refer code
            }
            val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Refer Code", referCode)
            clipboard.setPrimaryClip(clip)
        })

        binding.ibBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setupRecyclerView()

        loadRefer()

        return binding.root

    }

    // Function to generate referral URL
    private fun generateReferralUrl(baseUrl: String, referralCode: String): String {
        return "$baseUrl?ref=$referralCode"
    }

    // Function to share text and URL
    private fun shareTextAndUrl(message: String, url: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT, """
     $message
     $url
     """.trimIndent()
        )
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun setupRecyclerView() {
        referUserAdapter = ReferUserAdapter(requireActivity(), referPlans)
        binding.rvReferList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReferList.adapter = referUserAdapter
    }

    fun loadRefer() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.USER_ID] = session.getData(Constant.USER_ID)

        ApiConfig.RequestToVolley({ result: Boolean, response: String? ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val jsonArray = jsonObject.getJSONArray(Constant.DATA)
                        referPlans.clear()

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            val plansObject = jsonObject1.getJSONObject("plans")

                            val basicPlan = plansObject.optString("Basic Plan - ₹ 2999", "0")
                            val standardPlan = plansObject.optString("Standard Plan - ₹ 3999", "0")
                            val freeTrail = plansObject.optString("Free Trail Earning - 2 Days", "0")
                            val advancedPlan = plansObject.optString("Advanced Plan - ₹5999", "0")
                            val premiumPlan = plansObject.optString("Premium Plan - ₹ 11999", "0")

                            val mobile = jsonObject1.optString("mobile", "N/A")
                            val joinedDate = jsonObject1.optString("joined_date", "N/A")

                            val referPlan = ReferPlansModel(basicPlan, standardPlan, advancedPlan, premiumPlan, freeTrail, mobile, joinedDate)
                            referPlans.add(referPlan)
                        }

                        referUserAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.d("MY_TEAM", "MY_TEAM Error: " + e.message)
                    e.printStackTrace()
                }
            }
        }, activity, Constant.MY_TEAM, params, true)

        Log.d("MY_TEAM", "MY_TEAM: " + Constant.MY_TEAM)
        Log.d("MY_TEAM", "MY_TEAM params: $params")
    }

}