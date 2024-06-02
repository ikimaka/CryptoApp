package com.ikimaka.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ikimaka.cryptoapp.R
import com.ikimaka.cryptoapp.data.network.model.CoinInfoDto
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var onCoinClickListener: OnCoinClickListener? = null

    var coinInfoList: List<CoinInfoDto> = listOf()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_info, parent,false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList.get(position)

        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)

        holder.tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
        holder.tvPrice.text = coin.price
        holder.tvLastUpdate.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)

        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }


    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfoDto)
    }

    inner class CoinInfoViewHolder(itemView: View): ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvLastUpdate: TextView = itemView.findViewById(R.id.tvLastUpdate)
    }
}