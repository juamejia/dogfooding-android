package com.mercadolibre.android.dogfooding.testapp

import android.net.Uri
import android.os.Bundle
import com.mercadolibre.android.commons.core.model.SiteId
import com.mercadolibre.android.testing.basetestapp.AbstractTestAppLandingActivity
import com.mercadolibre.android.testing.basetestapp.countryutils.CountrySelector
import com.mercadolibre.android.testing.basetestapp.intentfactory.DeeplinkOnlyIntentFactory

/**
 * Main activity class
 */
class MainActivity : AbstractTestAppLandingActivity() {

    override fun getTestingModuleName(): String {
        return "dogfooding"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CountrySelector.Builder
                .into(this, findViewById(R.id.basetestapp_top_generic_container))
                .select(SiteId.MLA.ordinal)
                .build()

        addIntentStarter(
                DeeplinkOnlyIntentFactory("Dummy feature", Uri.parse("meli://dogfooding/dummy-feature")))
        addIntentStarter(
            DeeplinkOnlyIntentFactory("Developer Mode ", Uri.parse("meli://developer-mode/enabled")))
    }
}
