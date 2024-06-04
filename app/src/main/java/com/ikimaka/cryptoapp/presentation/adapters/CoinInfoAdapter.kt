package com.ikimaka.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikimaka.cryptoapp.R
import com.ikimaka.cryptoapp.databinding.ItemCoinInfoBinding
import com.ikimaka.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context): ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback)  {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)

        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)

        holder.binding.tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
        holder.binding.tvPrice.text = coin.price
        holder.binding.tvLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
        Picasso.get().load(coin.imageUrl).into(holder.binding.ivLogoCoin)

        holder.binding.root.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}