package com.ikimaka.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ikimaka.cryptoapp.adapters.CoinInfoAdapter
import com.ikimaka.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val rvCoinPriceList: RecyclerView = findViewById(R.id.rvCoinPriceList)

        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {

                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
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