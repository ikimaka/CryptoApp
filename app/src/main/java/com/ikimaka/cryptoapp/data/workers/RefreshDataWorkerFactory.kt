package com.ikimaka.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.ikimaka.cryptoapp.data.database.CoinInfoDao
import com.ikimaka.cryptoapp.data.mapper.CoinMapper
import com.ikimaka.cryptoapp.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return RefreshDataWorker(appContext, workerParameters, coinInfoDao, apiService, mapper)
    }
}