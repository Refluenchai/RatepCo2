package com.example.ratepco2.data.repository

import com.example.ratepco2.data.apiclient.CarbonApiClient
import com.example.ratepco2.data.response.TreeBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class TreeCarbonRepositoryImpl @Inject constructor(): TreeCarbonRepository {
    override fun getNumberOfTrees(
        weight: Double,
        unit: String,
        treesCallback: (treesCallback: String) -> Unit
    ) {
        CarbonApiClient.carbonApiInterface.getNumberOfTrees(weight = weight, unit = unit)
            .enqueue(object : Callback, retrofit2.Callback<TreeBody> {
                override fun onResponse(call: Call<TreeBody>, response: Response<TreeBody>) {
                    response.body()?.let { treesResponse ->
                        val modification = (treesResponse.numberOfTrees + 1) * 3
                        treesCallback(modification.toString())
                    }
                }

                override fun onFailure(call: Call<TreeBody>, t: Throwable) {
                    treesCallback("-1")
                }
            })
    }
}