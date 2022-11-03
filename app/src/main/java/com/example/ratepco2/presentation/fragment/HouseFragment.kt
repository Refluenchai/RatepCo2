package com.example.ratepco2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.FragmentHouseBinding
import com.example.ratepco2.presentation.activity.MainActivity
import com.example.ratepco2.presentation.viewmodel.HouseViewModel
import com.example.ratepco2.util.ETWatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var peopleQtt: Int = 0
    private var gasEmission: Double = 0.0
    private var energyEmission: Double = 0.0
    private lateinit var binding: FragmentHouseBinding
    private lateinit var activity: MainActivity
    private val viewModel: HouseViewModel? by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHouseBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActivityProps()
        setIncProps()
        setObservers()
        setEvents()
    }

    private fun setEvents() {
        binding.run {
            fabNext.setOnClickListener { nextEvent() }
            incEnergy.etItemText.run {
                this.addTextChangedListener(ETWatcher(this, viewModel) {
                    activity.subtractEmission(energyEmission)
                    (it as HouseViewModel).getEnergyCarbonEquivalent(this.text.toString().toDouble())
                })
            }
            incGas.etItemText.run {
                this.addTextChangedListener(ETWatcher(this, viewModel) {
                    activity.subtractEmission(gasEmission)
                    (it as HouseViewModel).getGasCarbonEquivalent(this.text.toString().toInt())
                })
            }
            incPeople.spnItemMenu.onItemSelectedListener = this@HouseFragment
        }
    }

    private fun setObservers() {
        viewModel?.energyCarbonEquivalentLiveData?.observe(viewLifecycleOwner) { energyEmission ->
            this.energyEmission = energyEmission
            activity.addEmission(energyEmission)
        }

        viewModel?.gasCarbonEquivalentLiveData?.observe(viewLifecycleOwner) { gasEmission ->
            this.gasEmission = gasEmission
            activity.addEmission(gasEmission)
        }
    }

    private fun setActivityProps() {
        activity.binding.run {
            ivIcon.visibility = View.VISIBLE
            ivIcon.setImageResource(R.drawable.ic_home)
            tvTitle.text = getString(R.string.house_title)
            tvDescription.text = getString(R.string.house_description)
        }
    }

    private fun setIncProps() {
        binding.run {
            incEnergy.tvItemTitle.text = getString(R.string.house_energy_title)
            incEnergy.tvType.text = getString(R.string.item_menu_with_text_energy_by_month)
            incEnergy.spnItemMenu.visibility = View.GONE
            incEnergy.etItemText.hint = "0"

            incGas.tvItemTitle.text = getString(R.string.house_gas_title)
            incGas.tvType.text = getString(R.string.item_menu_with_text_cylinders_by_month)
            incGas.spnItemMenu.visibility = View.GONE
            incGas.etItemText.hint = "0"

            incPeople.tvItemTitle.text = getString(R.string.house_people_title)
            incPeople.etItemText.visibility = View.GONE
            incPeople.spnItemMenu.adapter = ArrayAdapter(
                this@HouseFragment.requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            )
            this@HouseFragment.peopleQtt = incPeople.spnItemMenu.selectedItemPosition
        }
    }

    private fun nextEvent() = goToIndividualTransport()

    private fun goToIndividualTransport() = findNavController().navigate(
        HouseFragmentDirections
            .actionHouseFragmentToIndividualTransportFragment()
    )

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if (this.peopleQtt != 0) {
            activity.multiplyEmission(this.peopleQtt)
            this.peopleQtt = parent?.getItemAtPosition(position).toString().toInt()
            activity.divideEmission(this.peopleQtt)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}