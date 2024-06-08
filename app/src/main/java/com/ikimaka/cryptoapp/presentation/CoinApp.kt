package com.ikimaka.cryptoapp.presentation

import android.app.Application
import com.ikimaka.cryptoapp.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}