package com.ikimaka.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.ikimaka.cryptoapp.data.database.AppDatabase
import com.ikimaka.cryptoapp.data.database.CoinInfoDao
import com.ikimaka.cryptoapp.data.mapper.CoinMapper
import com.ikimaka.cryptoapp.data.network.ApiFactory
import com.ikimaka.cryptoapp.data.workers.RefreshDataWorker
import com.ikimaka.cryptoapp.domain.CoinInfo
import com.ikimaka.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinInfoList = coinInfoDao.getPriceList()
        return coinInfoList.map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        val coinInfo = coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
        return coinInfo.map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(RefreshDataWorker.NAME, ExistingWorkPolicy.REPLACE, RefreshDataWorker.makeRequest())
    }











}