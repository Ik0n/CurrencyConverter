package ru.ikon.currencyconverter.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency_rate.view.*
import ru.ikon.currencyconverter.R

class CurrencyRateAdapter(var rates: Map<String, Double>) : RecyclerView.Adapter<CurrencyRateAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(code: String, rate: Double) {
            itemView.tv_currency_code.text = code
            itemView.tv_currency_rate.text = rate.toString()

            itemView.setOnClickListener {
                this@CurrencyRateAdapter.listener.onItemClick(code, rate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency_rate, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rates.keys.elementAt(position), rates.values.elementAt(position))
    }

    override fun getItemCount(): Int = rates.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}