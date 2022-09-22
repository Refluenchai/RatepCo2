package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentWhatIsItBinding
import com.example.ratepco2.presentation.activity.MainActivity

class WhatIsItFragment : Fragment() {

    private lateinit var binding: FragmentWhatIsItBinding
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhatIsItBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActivityProps()
        setStartCalcEvent()
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.visibility = View.GONE
            tvTitle.text = getString(R.string.what_is_it_title)
            tvDescription.text = getString(R.string.what_is_it_description)
        }
    }

    private fun setStartCalcEvent() {
        binding.btnStartCalc.setOnClickListener {
            findNavController().navigate(WhatIsItFragmentDirections
                .actionWhatIsItFragmentToHouseFragment())
        }
    }
}