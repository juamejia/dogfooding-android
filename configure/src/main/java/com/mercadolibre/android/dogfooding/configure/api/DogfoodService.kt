package com.mercadolibre.android.dogfooding.configure.api

import com.mercadolibre.android.restclient.RepositoryFactory
import retrofit2.Response
import retrofit2.http.GET

internal interface DogfoodService {
    @GET("users/validate")
    suspend fun validate(): Response<Unit>
}

internal fun dogfoodService(): DogfoodService {
    return RepositoryFactory.newBuilder(BASE_URL)
        .create(DogfoodService::class.java)
}

private const val BASE_URL = "https://api.mercadolibre.com/public/dogfooding/"