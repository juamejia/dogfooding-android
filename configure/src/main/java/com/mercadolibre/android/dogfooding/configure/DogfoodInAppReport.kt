package com.mercadolibre.android.dogfooding.configure

import com.mercadolibre.android.authentication.AuthenticationFacade
import com.mercadolibre.android.dogfooding.configure.api.DogfoodService
import com.mercadolibre.android.in_app_report.configure.Settings
import com.mercadolibre.android.in_app_report.configure.setting
import retrofit2.HttpException
import retrofit2.Response

class DogfoodInAppReport(
    private val service: DogfoodService
) {

    internal fun settings(isDebug: Boolean): Settings {
        return setting {
            debug(isDebug)
            enabler {
                val isUserLogged = AuthenticationFacade.isUserLogged()
                val isUserDogfooder = validateUserDogfood()
                isDebug.not() and isUserLogged and isUserDogfooder
            }
        }
    }

    private suspend fun validateUserDogfood() = validateUser {
        validate()
    }

    private suspend fun validateUser(call: suspend DogfoodService.() -> Response<Unit>) = try {
        service.call().isSuccessful
    } catch (e: HttpException) {
        false
    }
}