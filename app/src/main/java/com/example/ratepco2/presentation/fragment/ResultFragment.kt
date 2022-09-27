package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentResultBinding
import com.example.ratepco2.presentation.activity.MainActivity
import com.example.ratepco2.presentation.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val viewModel: ResultViewModel by viewModels()
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setObservers()
        viewModel.getNumberOfTrees(binding.tvTonCo2.text.toString().toDouble())
    }

    private fun setObservers() {
        viewModel.numberOfTreesLiveData.observe(viewLifecycleOwner) { numberOfTrees ->
            binding.tvNeededTree.text = numberOfTrees.toString()
        }
    }

    private fun setProps() {
        setActivityProps()
        binding.tvTonCo2.text = activity.binding.tvPartialResult.text.toString()
    }

    override fun onPause() {
        super.onPause()
        activity.binding.tvDescription.visibility = View.VISIBLE
        activity.colorBackground()
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.setImageResource(R.drawable.ic_calculate)
            tvTitle.text = getString(R.string.result_title)
            tvDescription.visibility = View.GONE
            clPartialResult.visibility = View.GONE
            activity.colorBackground(true)
        }
    }
}