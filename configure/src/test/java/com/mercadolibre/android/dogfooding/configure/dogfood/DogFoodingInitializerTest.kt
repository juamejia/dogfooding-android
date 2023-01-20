package com.mercadolibre.android.dogfooding.configure.dogfood

import android.content.Context
import com.mercadolibre.android.dogfooding.configure.DogFoodingInitializer
import io.mockk.verify
import io.mockk.mockk
import org.junit.Test

class DogFoodingInitializerTest {

    @Test
    fun `configure should call settings method with correct isDebug value`() {
        val context = mockk<Context>()
        val dogFoodingInitializer = DogFoodingInitializer(true)
        dogFoodingInitializer.configure(context)
        verify { dogFoodingInitializer.dogFoodingInaAppReport.settings(true) }
    }

    @Test
    fun `configure should call dogfoodService method`() {
        val context = mockk<Context>()
        val dogFoodingInitializer = DogFoodingInitializer(true)
        dogFoodingInitializer.configure(context)
        verify { dogFoodingInitializer.dogFoodingInaAppReport }
    }

}