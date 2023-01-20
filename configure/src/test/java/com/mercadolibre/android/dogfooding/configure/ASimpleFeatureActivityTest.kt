package com.mercadolibre.android.dogfooding.configure

import android.content.Intent
import com.mercadolibre.android.testing.AbstractActivityTest
import com.mercadolibre.android.testing.AbstractActivityTest.ActivityControllerFactory
import org.junit.Assert
import org.junit.Test

class ASimpleFeatureActivityTest : AbstractActivityTest<ASimpleFeatureActivity>() {
    @Test
    fun testASimpleFeatureActivity_withExtra_receivesUrlExtra() {
        val url = "url"
        val urlTest = "urlTest"
        val intent = Intent()
        intent.putExtra(url, urlTest)
        val aSimpleFeatureActivity = startActivity(ASimpleFeatureActivity::class.java, intent).get()
        Assert.assertNotNull(
            "The received URL must not be null",
            aSimpleFeatureActivity?.intent?.extras?.getSerializable(url)
        )
        Assert.assertEquals(
            "The received URL is wrong",
            urlTest,
            aSimpleFeatureActivity?.intent?.extras?.getString(url)
        )
    }

    override fun getActivityControllers(): List<ActivityControllerFactory<ASimpleFeatureActivity>> {
        val controllerFactories: MutableList<ActivityControllerFactory<ASimpleFeatureActivity>> =
            ArrayList()
        // Add one controller factory that creates the activity without a specific intent
        controllerFactories.add(ActivityControllerFactory { bundle ->
            val controller = createActivity(ASimpleFeatureActivity::class.java, bundle)
            // Assertions after activity created
            Assert.assertNotNull("Activity must NOT be null", controller.get())
            controller
        })
        return controllerFactories
    }
}
