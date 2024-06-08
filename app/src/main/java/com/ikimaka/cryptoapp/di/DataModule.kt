package com.ikimaka.cryptoapp.di

import android.app.Application
import com.ikimaka.cryptoapp.data.database.AppDatabase
import com.ikimaka.cryptoapp.data.database.CoinInfoDao
import com.ikimaka.cryptoapp.data.repository.CoinRepositoryImpl
import com.ikimaka.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository


    companion object {
        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}