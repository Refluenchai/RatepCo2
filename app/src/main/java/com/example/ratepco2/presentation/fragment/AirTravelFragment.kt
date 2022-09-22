package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentAirTravelBinding
import com.example.ratepco2.presentation.activity.MainActivity

class AirTravelFragment : Fragment() {

    private lateinit var binding: FragmentAirTravelBinding
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
        setNextEvent()
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
            incDeparture.tvItemTitle.text = getString(R.string.air_travel_departure_title)
            incDeparture.etItemText.visibility = View.GONE
            incDeparture.spnItemMenu.adapter = ArrayAdapter(
                this@AirTravelFragment.requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            )

            incArrival.tvItemTitle.text = getString(R.string.air_travel_arrival_title)
            incArrival.etItemText.visibility = View.GONE
            incArrival.spnItemMenu.adapter = ArrayAdapter(
                this@AirTravelFragment.requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            )

            incTypeOfTrip.tvItemTitle.text = getString(R.string.air_travel_type_of_trip_title)
            incTypeOfTrip.etItemText.visibility = View.GONE
            incTypeOfTrip.spnItemMenu.adapter = ArrayAdapter(
                this@AirTravelFragment.requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.type_of_trip)
            )

            incNumberOfTrips.tvItemTitle.text = getString(R.string.air_travel_number_of_trips_title)
            incNumberOfTrips.spnItemMenu.visibility = View.GONE
            incNumberOfTrips.etItemText.gravity = Gravity.CENTER or Gravity.START
            incNumberOfTrips.etItemText.setText("0")
        }
    }

    private fun setNextEvent() = binding.fabNext.setOnClickListener { nextEvent() }

    private fun nextEvent() = goToDiet()

    private fun goToDiet() = findNavController().navigate(
        AirTravelFragmentDirections.actionAirTravelFragmentToDietFragment()
    )
}