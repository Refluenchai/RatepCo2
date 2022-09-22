package com.example.ratepco2.presentation.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.DialogGroundTransportationBinding
import com.example.ratepco2.domain.model.Vehicle
import com.example.ratepco2.presentation.viewmodel.GroundTransportationViewModel
import com.example.ratepco2.util.enumerator.TransportEnum.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GroundTransportationDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogGroundTransportationBinding
    private val viewModel: GroundTransportationViewModel by viewModels()
    private var isIndividual: Boolean = true
    private var vehicleEmission: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogGroundTransportationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        isIndividual = arguments?.get("isIndividual") as Boolean
        setIncProps()
        setEvents()
        setObservers()
    }

    private fun setObservers() {
        viewModel.carCarbonEquivalentLiveData.observe(viewLifecycleOwner) { carEmission ->
            vehicleEmission = carEmission
            goBackWithArguments()
        }

        viewModel.motorBikeCarbonEquivalentLiveData.observe(viewLifecycleOwner) { motorBikeEmission ->
            vehicleEmission = motorBikeEmission
            goBackWithArguments()
        }

        viewModel.publicTransitCarbonEquivalentLiveData.observe(viewLifecycleOwner) { publicTransitEmission ->
            vehicleEmission = publicTransitEmission
            goBackWithArguments()
        }
    }

    private fun setIncProps() {
        binding.run {
            incTransportType.run {
                tvItemTitle.text = getString(R.string.ground_transportation_type_title)
                etItemText.visibility = View.GONE
                spnItemMenu.adapter = ArrayAdapter(
                    this@GroundTransportationDialog.requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    resources.getStringArray(R.array.transport_type)
                )
            }

            incKmMonth.run {
                tvItemTitle.text = getString(R.string.item_menu_with_text_total_km_month)
                spnItemMenu.visibility = View.GONE
                etItemText.gravity = Gravity.CENTER or Gravity.START
                etItemText.hint = getString(R.string.item_menu_with_text_total_km_month)
            }
        }
    }

    private fun setEvents() {
        binding.run {
            btnAddTransport.setOnClickListener { addTransportEvent() }
        }
    }

    private fun addTransportEvent() {
        binding.run {
            when (incTransportType.spnItemMenu.selectedItem) {
                CAR.ordinal -> viewModel.getCarCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "MediumLPGCar"
                )
                MOTORCYCLE.ordinal -> viewModel.getMotorBikeCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "MediumMotorBike"
                )
                BIKE.ordinal -> {}
                ELECTRIC_BIKE.ordinal -> viewModel.getMotorBikeCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "SmallMotorBike"
                )
                BUS.ordinal -> viewModel.getPublicTransitCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "ClassicBus"
                )
                TRAIN.ordinal -> viewModel.getPublicTransitCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "NationalTrain"
                )
                else -> viewModel.getPublicTransitCarbonEquivalent(
                    incKmMonth.etItemText.text.toString().toDouble(), "Taxi"
                )
            }
        }
    }

    private fun goBackWithArguments() {
        findNavController().run {
            previousBackStackEntry?.savedStateHandle?.set("vehicle", getVehicle())
            navigateUp()
        }
    }

    private fun getVehicle(): Vehicle = Vehicle(type = getVehicleType(), emission = vehicleEmission)

    private fun getVehicleType(): Int =
        binding.incTransportType.spnItemMenu.selectedItemPosition
}