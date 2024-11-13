package com.example.privatcurrency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.privatcurrency.item.ExchangeRate
import com.example.privatcurrency.R

class CurrencyAdapter(
    private var currencyList: List<ExchangeRate>,
    private val onItemClick: (ExchangeRate) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currencyTextView: TextView = itemView.findViewById(R.id.currencyTextView)

        fun bind(exchangeRate: ExchangeRate, isSelected: Boolean) {
            currencyTextView.text = exchangeRate.currency ?: "Unknown"
            itemView.isSelected = isSelected  // Update selection state
            itemView.setBackgroundResource(
                if (isSelected) R.color.selected_item_color else R.color.default_item_color
            )
            itemView.setOnClickListener {
                // Save the old selected position to refresh it and update the new position
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onItemClick(exchangeRate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position], position == selectedPosition)
    }

    override fun getItemCount() = currencyList.size

    fun updateData(newCurrencyList: List<ExchangeRate>) {
        currencyList = newCurrencyList
        notifyDataSetChanged()
    }
}


//class CurrencyAdapter(
//    private var currencyList: List<ExchangeRate>,
//    private val onItemClick: (ExchangeRate) -> Unit
//) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
//
//    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val currencyTextView: TextView = itemView.findViewById(R.id.currencyTextView)
//
//        fun bind(exchangeRate: ExchangeRate) {
//            currencyTextView.text = exchangeRate.currency ?: "Unknown"
//            itemView.setOnClickListener {
//                onItemClick(exchangeRate)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
//        return CurrencyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
//        holder.bind(currencyList[position])
//    }
//
//    override fun getItemCount() = currencyList.size
//
//    fun updateData(newCurrencyList: List<ExchangeRate>) {
//        currencyList = newCurrencyList
//        notifyDataSetChanged()
//    }
//}
