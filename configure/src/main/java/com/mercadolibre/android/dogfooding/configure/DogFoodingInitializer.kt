package com.mercadolibre.android.dogfooding.configure

import android.content.Context
import com.mercadolibre.android.configuration.manager.Configurable
import com.mercadolibre.android.dogfooding.configure.api.dogfoodService
import com.mercadolibre.android.in_app_report.configure.InAppReportConfigure


class DogFoodingInitializer(private val isDebug: Boolean) : Configurable {

    private val dogFoodingInaAppReport = DogfoodInAppReport(dogfoodService())

    override fun configure(context: Context) =
        InAppReportConfigure.init(context, dogFoodingInaAppReport.settings(isDebug))


}