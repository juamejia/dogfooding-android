package com.mercadolibre.android.dogfooding.app

import android.os.Bundle
import com.mercadolibre.android.commons.core.AbstractActivity
import com.mercadolibre.android.dogfooding.configure.R

class ASimpleFeatureActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dogfooding_app_activity_asimple_feature)
    }
}