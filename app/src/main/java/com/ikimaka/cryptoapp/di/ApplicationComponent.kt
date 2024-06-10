package com.ikimaka.cryptoapp.di

import android.app.Application
import com.ikimaka.cryptoapp.presentation.CoinApp
import com.ikimaka.cryptoapp.presentation.CoinDetailFragment
import com.ikimaka.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}