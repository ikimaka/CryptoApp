package com.ikimaka.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ikimaka.cryptoapp.domain.CoinInfo
import com.ikimaka.cryptoapp.domain.GetCoinInfoListUseCase
import com.ikimaka.cryptoapp.domain.GetCoinInfoUseCase
import com.ikimaka.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject


class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
): ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fSym)
    }

    init {
        loadDataUseCase()
    }

}