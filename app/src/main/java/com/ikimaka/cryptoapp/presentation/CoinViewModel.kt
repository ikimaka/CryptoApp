package com.ikimaka.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ikimaka.cryptoapp.data.repository.CoinRepositoryImpl
import com.ikimaka.cryptoapp.domain.CoinInfo
import com.ikimaka.cryptoapp.domain.GetCoinInfoListUseCase
import com.ikimaka.cryptoapp.domain.GetCoinInfoUseCase
import com.ikimaka.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch


class CoinViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fSym)
    }

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}