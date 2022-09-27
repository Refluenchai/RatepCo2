package com.example.ratepco2.di

import com.example.ratepco2.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindEnergyCarbonRepository(energyCarbonRepositoryImpl: EnergyCarbonRepositoryImpl): EnergyCarbonRepository

    @Binds
    fun bindCarCarbonRepository(carbonRepository: CarCarbonRepositoryImpl): CarCarbonRepository

    @Binds
    fun bindMotorBikeRepository(motorBikeCarbonRepository: MotorBikeCarbonRepositoryImpl): MotorBikeCarbonRepository

    @Binds
    fun bindPublicTransitRepository(publicTransitRepository: PublicTransitRepositoryImpl): PublicTransitRepository

    @Binds
    fun bindFlightRepository(flightCarbonRepository: FlightCarbonRepositoryImpl): FlightCarbonRepository

    @Binds
    fun bindTreeRepository(treeCarbonRepository: TreeCarbonRepositoryImpl): TreeCarbonRepository
}