package com.example.ratepco2.data.repository

import com.example.ratepco2.data.apiclient.CarbonApiClient
import com.example.ratepco2.data.response.CarbonBody
import retrofit2.Call
import javax.security.auth.callback.Callback
import retrofit2.Response
import javax.inject.Inject

class CarCarbonRepositoryImpl @Inject constructor() : CarCarbonRepository {
    override fun getCarCarbon(
        distance: Double,
        type: String,
        carbonCallback: (carbonEquivalent: String) -> Unit
    ) {
        CarbonApiClient.carbonApiInterface.getCarCarbon(distance = distance, type = type)
            .enqueue(object : Callback, retrofit2.Callback<CarbonBody> {
                override fun onResponse(call: Call<CarbonBody>, response: Response<CarbonBody>) {
                    response.body()?.let { carbonResponse ->
                        carbonCallback(carbonResponse.carbon.replace(" kg co2", ""))
                    }
                }

                override fun onFailure(call: Call<CarbonBody>, t: Throwable) {
                    carbonCallback("-1")
                }
            })
    }
}