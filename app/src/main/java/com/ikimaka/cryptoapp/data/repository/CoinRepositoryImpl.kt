package com.ikimaka.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.ikimaka.cryptoapp.data.database.AppDatabase
import com.ikimaka.cryptoapp.data.mapper.CoinMapper
import com.ikimaka.cryptoapp.data.network.ApiFactory
import com.ikimaka.cryptoapp.domain.CoinInfo
import com.ikimaka.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

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

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 20)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(20000)
        }
    }











}