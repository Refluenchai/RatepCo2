package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentDietBinding
import com.example.ratepco2.presentation.activity.MainActivity

class DietFragment : Fragment() {

    private lateinit var binding: FragmentDietBinding
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDietBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActivityProps()
        setIncProps()
        setNextEvent()
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.visibility = View.VISIBLE
            ivIcon.setImageResource(R.drawable.ic_food)
            tvTitle.text = getString(R.string.diet_title)
            tvDescription.text = getString(R.string.diet_description)
        }
    }

    private fun setIncProps() {
        binding.run {
            incTypeOfDiet.tvItemTitle.text = getString(R.string.diet_type_of_diet_title)
            incTypeOfDiet.etItemText.visibility = View.GONE
            incTypeOfDiet.spnItemMenu.adapter = ArrayAdapter(
                this@DietFragment.requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.type_of_diet)
            )
        }
    }

    private fun setNextEvent() = binding.fabResult.setOnClickListener { resultEvent() }

    private fun resultEvent() = goToResult()

    private fun goToResult() = findNavController().navigate(
        DietFragmentDirections.actionDietFragmentToResultFragment()
    )

}