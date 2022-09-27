package com.example.ratepco2.data.repository

import com.example.ratepco2.data.apiclient.CarbonApiClient
import com.example.ratepco2.data.response.CarbonBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class FlightCarbonRepositoryImpl @Inject constructor(): FlightCarbonRepository {
    override fun getFlightCarbon(
        distance: Double,
        type: String,
        carbonCallback: (carbonEquivalent: String) -> Unit
    ) {
        CarbonApiClient.carbonApiInterface.getFlightCarbon(distance = distance, type = type)
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