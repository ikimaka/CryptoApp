package com.ikimaka.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ikimaka.cryptoapp.R
import com.ikimaka.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.ikimaka.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.ikimaka.cryptoapp.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {

                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }

    }


}