package com.ikimaka.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.ikimaka.cryptoapp.data.database.AppDatabase
import com.ikimaka.cryptoapp.data.mapper.CoinMapper
import com.ikimaka.cryptoapp.data.network.ApiFactory
import com.ikimaka.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.ikimaka.cryptoapp.di.DaggerApplicationComponent

class CoinApp: Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            ).build()



}