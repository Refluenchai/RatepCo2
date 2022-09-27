package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentAirTravelBinding
import com.example.ratepco2.presentation.activity.MainActivity
import com.example.ratepco2.presentation.viewmodel.AirTravelViewModel
import com.example.ratepco2.presentation.viewmodel.HouseViewModel
import com.example.ratepco2.util.ETWatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirTravelFragment : Fragment() {

    private var flightEmission: Double = 0.0
    private lateinit var binding: FragmentAirTravelBinding
    private val viewModel: AirTravelViewModel by viewModels()
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirTravelBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActivityProps()
        setIncProps()
        setEvents()
        setObservers()
    }

    private fun setObservers() {
        viewModel.flightCarbonEquivalentLiveData.observe(viewLifecycleOwner) { flightEmission ->
            this.flightEmission = flightEmission
            activity.addEmission(flightEmission)
        }
    }

    private fun setEvents() {
        binding.run {
            fabNext.setOnClickListener { nextEvent() }
            incKmMonth.etItemText.run {
                addTextChangedListener(ETWatcher(this, viewModel) {
                    activity.subtractEmission(flightEmission)
                    (it as AirTravelViewModel).getFlightCarbonEquivalent(
                        this.text.toString().toDouble(), "DomesticFlight"
                    )
                })
            }
        }
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.visibility = View.VISIBLE
            ivIcon.setImageResource(R.drawable.ic_airplane)
            tvTitle.text = getString(R.string.air_travel_title)
            tvDescription.text = getString(R.string.air_travel_description)
        }
    }

    private fun setIncProps() {
        binding.run {
            incKmMonth.run {
                tvItemTitle.text = getString(R.string.item_menu_with_text_total_km_month)
                spnItemMenu.visibility = View.GONE
                etItemText.gravity = Gravity.CENTER or Gravity.START
                etItemText.hint = getString(R.string.item_menu_with_text_total_km_month)
            }
        }
    }

    private fun nextEvent() = goToDiet()

    private fun goToDiet() = findNavController().navigate(
        AirTravelFragmentDirections.actionAirTravelFragmentToResultFragment()
    )
}