package com.example.ratepco2.presentation.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ratepco2.databinding.ItemVehicleBinding
import com.example.ratepco2.domain.model.Vehicle
import com.example.ratepco2.util.getVehicleImage
import com.example.ratepco2.util.getVehicleTypeText

class VehicleViewHolder(private val binding: ItemVehicleBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(vehicle: Vehicle, context: Context, isTheFirst: Boolean) {
        vehicle.run {
            binding.run {
                viewHorizontal.visibility = if (isTheFirst) View.GONE else View.VISIBLE
                ivVehicleIcon.setImageDrawable(getVehicleImage(type, context))
                tvVehicle.text = getVehicleTypeText(type, context)
                tvEmission.text = emission.toString()
            }
        }
    }
}