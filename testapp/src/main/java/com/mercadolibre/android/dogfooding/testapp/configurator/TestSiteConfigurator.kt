package com.mercadolibre.android.dogfooding.testapp.configurator

import android.content.Context
import com.mercadolibre.android.commons.core.model.SiteId
import com.mercadolibre.android.commons.core.utils.CountryConfigManager
import com.mercadolibre.android.configuration.manager.Configurable
import com.mercadolibre.android.configuration.manager.priorities.Priorities
import com.mercadolibre.android.testing.basetestapp.countryutils.configurator.SiteDataSource
import com.mercadolibre.android.testing.basetestapp.countryutils.configurator.SiteUtils

class TestSiteConfigurator : Configurable {

    override fun getPriority(): Int {
        return Priorities.CRITICAL
    }

    override fun configure(context: Context) {

        val dataSource = object : SiteDataSource {

            override fun getSites(): Array<String> {
                return SiteId.values().contentToString().replace("^.|.$".toRegex(), "")
                        .split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }

            override fun updateCountry(siteId: Int, context: Context) {
                CountryConfigManager.updateCountry(SiteId.values()[siteId], context)
            }
        }

        SiteUtils.setup(dataSource)
    }
}
