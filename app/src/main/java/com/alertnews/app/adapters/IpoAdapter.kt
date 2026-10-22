package com.alertnews.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget DiffUtil
import androidx.recyclerview.widget ListAdapter
import com.alertnews.app.R
import com.alertnews.app.data.model.IpoEvent

class IpoAdapter : ListAdapter<IpoEvent, IpoAdapter.IpoViewHolder>(DiffCallback()) {

    class IpoViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val company: TextView = view.findViewById(R.id.ipo_company)
        val symbol: TextView = view.findViewById(R.id.ipo_symbol)
        val exchange: TextView = view.findViewById(R.id.ipo_exchange)
        val date: TextView = view.findViewById(R.id.ipo_date)
        val price: TextView = view.findViewById(R.id.ipo_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IpoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ipo, parent, false)
        return IpoViewHolder(view)
    }

    override fun onBindViewHolder(holder: IpoViewHolder, position: Int) {
        val ipo = getItem(position)
        holder.company.text = ipo.companyName
        holder.symbol.text = ipo.symbol
        holder.exchange.text = ipo.exchange
        holder.date.text = ipo.date
        holder.price.text = ipo.priceRange
    }

    class DiffCallback : DiffUtil.ItemCallback<IpoEvent>() {
        override fun areItemsTheSame(old: IpoEvent, new: IpoEvent): Boolean {
            return old.companyName == new.companyName
        }

        override fun areContentsTheSame(old: IpoEvent, new: IpoEvent): Boolean {
            return old == new
        }
    }
}