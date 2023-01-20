package com.mercadolibre.android.dogfooding.configure.dogfood

import com.mercadolibre.android.authentication.AuthenticationFacade
import com.mercadolibre.android.dogfooding.configure.DogFoodingInitializer
import com.mercadolibre.android.dogfooding.configure.DogfoodInAppReport
import com.mercadolibre.android.dogfooding.configure.api.DogfoodService
import com.mercadolibre.android.in_app_report.core.infrastructure.services.api.service
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.net.HttpURLConnection
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test

class DogfoodInAppReportTest {

    private lateinit var service: DogfoodService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/").toString()
        service = service(baseUrl)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `validate settings for enable report`() {
        mockkStatic(AuthenticationFacade::class)

        val dogfoodInAppReport = DogfoodInAppReport(service)
        val debug = false
        every { AuthenticationFacade.isUserLogged() } returns true

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
        mockWebServer.enqueue(response)

        runBlocking {
            val result = dogfoodInAppReport.settings(debug).enabler?.invoke()
            assertThat(result, IsEqual(true))
        }

        unmockkStatic(AuthenticationFacade::class)
    }

    @Test
    fun `validate settings for not enable report`() {
        mockkStatic(AuthenticationFacade::class)

        val dogfoodInAppReport = DogfoodInAppReport(service)
        val debug = true
        every { AuthenticationFacade.isUserLogged() } returns false

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(response)

        runBlocking {
            val result = dogfoodInAppReport.settings(debug).enabler?.invoke()
            assertThat(result, IsEqual(false))
        }

        unmockkStatic(AuthenticationFacade::class)
    }
}