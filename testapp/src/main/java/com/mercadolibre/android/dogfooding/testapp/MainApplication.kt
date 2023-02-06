package com.mercadolibre.android.dogfooding.testapp

import android.app.Application
import com.mercadolibre.android.authentication.AuthenticationManager
import com.mercadolibre.android.configuration.manager.ConfigurationManager
import com.mercadolibre.android.developer_mode.DeveloperModeConfigurer
import com.mercadolibre.android.devices_sdk.devices.storage.local.LocalStorageConfigurator
import com.mercadolibre.android.testing.basetestapp.configurator.TestAppConfigurationManager

/**
 * Main Application class that extends from Application to execute the start method only once.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // First we setup debugging tools to check whether the app creation is normal or not.
        if (!TestAppConfigurationManager.configure(this)) {
            return
        }
        DeveloperModeConfigurer().configure(this)
        AuthenticationManager.getInstance().init(this, "1");
        ConfigurationManager.configure(this)
        if (BuildConfig.FLAVOR == "mercadopago"){
            LocalStorageConfigurator().configure(this)
        }
    }
}
