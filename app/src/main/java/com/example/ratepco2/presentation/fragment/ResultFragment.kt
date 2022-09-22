package com.example.ratepco2.presentation.fragment

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentDietBinding
import com.example.ratepco2.databinding.FragmentResultBinding
import com.example.ratepco2.presentation.activity.MainActivity

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
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
        setActivityProps()
    }

    override fun onPause() {
        super.onPause()
        activity.binding.tvDescription.visibility = View.VISIBLE
        activity.colorBackground()
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.setImageResource(R.drawable.ic_calculate)
            tvTitle.text = getString(R.string.diet_title)
            tvDescription.text = getString(R.string.diet_description)
            tvDescription.visibility = View.GONE
            clPartialResult.visibility = View.GONE
            activity.colorBackground(true)
        }
    }
}