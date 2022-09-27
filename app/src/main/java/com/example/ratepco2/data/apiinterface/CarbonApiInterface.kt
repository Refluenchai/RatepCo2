package com.example.ratepco2.data.apiinterface

import com.example.ratepco2.RatepCO2Application
import com.example.ratepco2.data.response.CarbonBody
import com.example.ratepco2.data.response.TreeBody
import retrofit2.Call
import retrofit2.http.*

interface CarbonApiInterface {

    @POST("cleanHydro")
    fun getCleanEnergyCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("energy") energy: String = "HydroElectric",
        @Query("consumption") consumption: Double
    ): Call<CarbonBody>

    @POST("carTravel")
    fun getCarCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("distance") distance: Double,
        @Query("vehicle") type: String
    ): Call<CarbonBody>

    @POST("motorBike")
    fun getMotorBikeCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("distance") distance: Double,
        @Query("type") type: String
    ): Call<CarbonBody>

    @POST("publicTransit")
    fun getPublicTransitCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("distance") distance: Double,
        @Query("type") type: String
    ): Call<CarbonBody>

    @POST("flight")
    fun getFlightCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("distance") distance: Double,
        @Query("type") type: String
    ): Call<CarbonBody>

    @POST("treeEquivalent")
    fun getNumberOfTrees(
        @Header("Authorization") authHeader: String = "Bearer " + RatepCO2Application().authHeaderToken,
        @Query("weight") weight: Double,
        @Query("unit") unit: String
    ): Call<TreeBody>
}