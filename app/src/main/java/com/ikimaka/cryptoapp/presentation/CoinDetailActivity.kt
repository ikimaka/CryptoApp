package com.ikimaka.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ikimaka.cryptoapp.R
import com.ikimaka.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.ikimaka.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var tvPrice: TextView
    private lateinit var tvMinPrice: TextView
    private lateinit var tvMaxPrice: TextView
    private lateinit var tvLastMarket: TextView
    private lateinit var tvLastUpdate: TextView
    private lateinit var tvFromSymbol: TextView
    private lateinit var tvToSymbol: TextView
    private lateinit var ivLogoCoinDetail: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        initViews()

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.getDetailInfo(fromSymbol).observe(this)  {
            tvPrice.text = it.price
            tvMinPrice.text = it.lowDay
            tvMaxPrice.text = it.highDay
            tvLastMarket.text = it.lastMarket
            tvLastUpdate.text = convertTimestampToTime(it.lastUpdate)
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(BASE_IMAGE_URL + it.imageUrl).into(ivLogoCoinDetail)
        }
    }

    private fun initViews() {
        tvPrice = findViewById(R.id.tvPrice)
        tvMinPrice = findViewById(R.id.tvMinPrice)
        tvMaxPrice = findViewById(R.id.tvMaxPrice)
        tvLastMarket = findViewById(R.id.tvLastMarket)
        tvLastUpdate = findViewById(R.id.tvLastUpdate)
        tvFromSymbol = findViewById(R.id.tvFromSymbol)
        tvToSymbol = findViewById(R.id.tvToSymbol)
        ivLogoCoinDetail = findViewById(R.id.ivLogoCoinDetail)
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)

            return intent
        }
    }


}