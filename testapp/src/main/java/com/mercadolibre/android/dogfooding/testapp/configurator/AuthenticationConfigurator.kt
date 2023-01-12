package com.mercadolibre.android.dogfooding.testapp.configurator

import android.content.Context
import android.net.Uri
import com.mercadolibre.android.authentication.AuthenticationFacade
import com.mercadolibre.android.commons.logging.Log
import com.mercadolibre.android.configuration.manager.Configurable
import com.mercadolibre.android.configuration.manager.priorities.Priorities
import com.mercadolibre.android.testing.basetestapp.authentication.AuthenticationDataSource
import com.mercadolibre.android.testing.basetestapp.authentication.AuthenticationUtils

class AuthenticationConfigurator : Configurable {

    override fun getPriority(): Int {
        return Priorities.MEDIUM
    }

    override fun configure(context: Context) {
        Log.i(this, "Running test app's authentication configurator...")
        val dataSource: AuthenticationDataSource = object : AuthenticationDataSource {
            override fun isUserLogged(): Boolean {
                return AuthenticationFacade.isUserLogged()
            }

            override fun logout() {
                AuthenticationFacade.logout()
            }

            override fun getLoginUri(): Uri {
                return Uri.parse(LOGIN_DEEP_LINK)
            }

            override fun getNickname(): String {
                return AuthenticationFacade.getNickname()!!
            }

            override fun getUserId(): String {
                return AuthenticationFacade.getUserId()!!
            }
        }
        AuthenticationUtils.setup(dataSource)
    }

    companion object {
        /**
         * Check [Site Security Fake Login]
         * (https://github.com/mercadolibre/auth-android_authentication/wiki/Authentication-Test-UI)
         * for Mercado Libre/Pago login integrations.
         */
        private const val LOGIN_DEEP_LINK = "meli://login/fake"
    }
}
