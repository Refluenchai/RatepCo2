package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentGroundTransportationBinding
import com.example.ratepco2.domain.model.Vehicle
import com.example.ratepco2.presentation.activity.MainActivity
import com.example.ratepco2.presentation.adapter.VehicleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroundTransportationFragment : Fragment() {

    private lateinit var binding: FragmentGroundTransportationBinding
    private lateinit var activity: MainActivity
    private val vehicles: MutableList<Vehicle> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroundTransportationBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        setObserver()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActivityProps()
        setEvents()
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.setImageResource(R.drawable.ic_car)
            tvTitle.text = getString(R.string.ground_transportation_title)
            tvDescription.text = getString(R.string.ground_transportation_description)
        }
    }

    private fun setObserver() = getDialogBackArgsObservers()

    private fun getDialogBackArgsObservers() {
        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Vehicle>("vehicle")
            ?.observe(viewLifecycleOwner) { vehicle ->
                activity.addEmission(vehicle.emission)
                vehicles.add(vehicle)
                setHintVisibility()
                setVehiclesInRecycler(vehicles)
            }
    }

    private fun setHintVisibility() {
        binding.run {
            tvVehicleType.visibility = View.VISIBLE
            tvEmission.visibility = View.VISIBLE
            tvHint.visibility = View.GONE
        }
    }

    private fun setVehiclesInRecycler(vehicles: MutableList<Vehicle>) {
        binding.rvVehicles.run {
            layoutManager = LinearLayoutManager(this@GroundTransportationFragment.requireContext())
            adapter = VehicleAdapter(vehicles, requireContext())
        }
    }

    private fun setEvents() {
        binding.run {
            fabAdd.setOnClickListener { addVehicleEvent() }
            fabNext.setOnClickListener { nextEvent() }
        }
    }

    private fun addVehicleEvent() {
        findNavController().navigate(
            GroundTransportationFragmentDirections.actionIndividualTransportFragmentToTransportationDialog(
                true
            )
        )
    }

    private fun nextEvent() {
        findNavController().navigate(GroundTransportationFragmentDirections.actionIndividualTransportFragmentToAirTravelFragment())
    }
}