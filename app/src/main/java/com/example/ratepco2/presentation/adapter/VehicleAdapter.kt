package com.example.ratepco2.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ratepco2.databinding.ItemVehicleBinding
import com.example.ratepco2.domain.model.Vehicle
import com.example.ratepco2.presentation.viewholder.VehicleViewHolder

class VehicleAdapter(
    private val vehicles: List<Vehicle>,
    private val context: Context): RecyclerView.Adapter<VehicleViewHolder>() {

    private lateinit var binding: ItemVehicleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemVehicleBinding.inflate(inflater, parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles[position], context, position == 0)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }
}