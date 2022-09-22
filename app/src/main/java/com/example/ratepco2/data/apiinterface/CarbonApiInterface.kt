package com.example.ratepco2.data.apiinterface

import com.example.ratepco2.data.response.CarbonBody
import retrofit2.Call
import retrofit2.http.*

interface CarbonApiInterface {

    @POST("cleanHydro")
    fun getCleanEnergyCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiM2VmYmFkYjljYjY1YmFhYTFkMTA0Y2M4NWUzNTc1N2E1OWU3ZGRkODdiMjcxNTk5ZTRhY2ZjMDk4ZjkwNDhkYzBjZjdiZDRiMTc2ZmJiYTUiLCJpYXQiOjE2NjE1MTk5MTksIm5iZiI6MTY2MTUxOTkxOSwiZXhwIjoxNjkzMDU1OTE5LCJzdWIiOiIxMzY0Iiwic2NvcGVzIjpbXX0.PPz4NmLY6X21ik27wPclXaqotvd1eG61hbVqmXkbEPGBzrme6dQJqi6K_g7Y2D_04Vh7bdhpzXT7a5r8U6BirA",
        @Query("energy") energy: String = "HydroElectric",
        @Query("consumption") consumption: Double
    ): Call<CarbonBody>

    @POST("carTravel")
    fun getCarCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiM2VmYmFkYjljYjY1YmFhYTFkMTA0Y2M4NWUzNTc1N2E1OWU3ZGRkODdiMjcxNTk5ZTRhY2ZjMDk4ZjkwNDhkYzBjZjdiZDRiMTc2ZmJiYTUiLCJpYXQiOjE2NjE1MTk5MTksIm5iZiI6MTY2MTUxOTkxOSwiZXhwIjoxNjkzMDU1OTE5LCJzdWIiOiIxMzY0Iiwic2NvcGVzIjpbXX0.PPz4NmLY6X21ik27wPclXaqotvd1eG61hbVqmXkbEPGBzrme6dQJqi6K_g7Y2D_04Vh7bdhpzXT7a5r8U6BirA",
        @Query("distance") distance: Double,
        @Query("vehicle") type: String
    ): Call<CarbonBody>

    @POST("motorBike")
    fun getMotorBikeCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiM2VmYmFkYjljYjY1YmFhYTFkMTA0Y2M4NWUzNTc1N2E1OWU3ZGRkODdiMjcxNTk5ZTRhY2ZjMDk4ZjkwNDhkYzBjZjdiZDRiMTc2ZmJiYTUiLCJpYXQiOjE2NjE1MTk5MTksIm5iZiI6MTY2MTUxOTkxOSwiZXhwIjoxNjkzMDU1OTE5LCJzdWIiOiIxMzY0Iiwic2NvcGVzIjpbXX0.PPz4NmLY6X21ik27wPclXaqotvd1eG61hbVqmXkbEPGBzrme6dQJqi6K_g7Y2D_04Vh7bdhpzXT7a5r8U6BirA",
        @Query("distance") distance: Double,
        @Query("type") type: String
    ): Call<CarbonBody>

    @POST("publicTransit")
    fun getPublicTransitCarbon(
        @Header("Authorization") authHeader: String = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiM2VmYmFkYjljYjY1YmFhYTFkMTA0Y2M4NWUzNTc1N2E1OWU3ZGRkODdiMjcxNTk5ZTRhY2ZjMDk4ZjkwNDhkYzBjZjdiZDRiMTc2ZmJiYTUiLCJpYXQiOjE2NjE1MTk5MTksIm5iZiI6MTY2MTUxOTkxOSwiZXhwIjoxNjkzMDU1OTE5LCJzdWIiOiIxMzY0Iiwic2NvcGVzIjpbXX0.PPz4NmLY6X21ik27wPclXaqotvd1eG61hbVqmXkbEPGBzrme6dQJqi6K_g7Y2D_04Vh7bdhpzXT7a5r8U6BirA",
        @Query("distance") distance: Double,
        @Query("type") type: String
    ): Call<CarbonBody>
}