package com.mercadolibre.android.dogfooding.configure

import android.os.Bundle
import com.mercadolibre.android.commons.core.AbstractActivity

class ASimpleFeatureActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dogfooding_app_activity_asimple_feature)
    }
}
