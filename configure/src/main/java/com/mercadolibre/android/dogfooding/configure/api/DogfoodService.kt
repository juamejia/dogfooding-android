package com.mercadolibre.android.dogfooding.configure.api

import com.mercadolibre.android.commons.logging.Log
import com.mercadolibre.android.restclient.RepositoryFactory
import retrofit2.Response
import retrofit2.http.GET

interface DogfoodService {
    @GET("users/validate")
    suspend fun validate(): Response<Unit>
}

internal fun dogfoodService(): DogfoodService {
    try {
        return RepositoryFactory.newBuilder(BASE_URL)
            .create(DogfoodService::class.java)
    } catch (e: Exception) {
        Log.e("DogfoodService", "Error al crear la instancia de DogfoodService", e)
        throw e
    }
}

private const val BASE_URL = "https://api.mercadolibre.com/public/dogfooding/"