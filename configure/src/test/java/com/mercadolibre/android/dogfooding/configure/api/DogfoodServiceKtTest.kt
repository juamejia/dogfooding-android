package com.mercadolibre.android.dogfooding.configure.api

import io.mockk.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import kotlin.system.measureTimeMillis

class DogfoodServiceKtTest {

    private val dogfoodService: DogfoodService = mockk()

    @Test
    fun `validate should make a GET request to the correct endpoint`() = runBlocking {
        coEvery { dogfoodService.validate() } returns Response.success(Unit)
        dogfoodService.validate()
        coVerify { dogfoodService.validate() }
    }

    @Test
    fun `validate should return a success response from the server`() = runBlockingTest {
        coEvery { dogfoodService.validate() } returns Response.success(Unit)
        val response = dogfoodService.validate()
        assertEquals(200, response.code())
        assertEquals(Unit, response.body())
    }

    @Test(expected = IOException::class)
    fun `validate should throw an exception if the server is down`() = runBlockingTest {
        coEvery { dogfoodService.validate() } throws IOException()

        dogfoodService.validate()
    }

    @Test
    fun `validate should return a response in a reasonable amount of time`() = runBlockingTest {
        coEvery { dogfoodService.validate() } returns Response.success(Unit)
        val response = dogfoodService.validate()
        val elapsedTime = measureTimeMillis { response }
        assertTrue(elapsedTime < 1000)
    }
}