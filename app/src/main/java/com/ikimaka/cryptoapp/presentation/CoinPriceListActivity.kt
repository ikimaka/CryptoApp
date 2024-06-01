package com.ikimaka.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ikimaka.cryptoapp.R
import com.ikimaka.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.ikimaka.cryptoapp.data.model.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val rvCoinPriceList: RecyclerView = findViewById(R.id.rvCoinPriceList)

        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {

                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })





    }


}