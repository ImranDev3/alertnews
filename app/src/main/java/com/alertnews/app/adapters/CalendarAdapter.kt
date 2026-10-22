package com.alertnews.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.alertnews.app.R
import com.alertnews.app.data.model.EconomicCalendar

class CalendarAdapter : ListAdapter<EconomicCalendar, CalendarAdapter.CalendarViewHolder>(DiffCallback()) {

    class CalendarViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.calendar_time)
        val eventName: TextView = view.findViewById(R.id.calendar_event)
        val currency: TextView = view.findViewById(R.id.calendar_currency)
        val impact: TextView = view findViewById(R.id.calendar_impact)
        val actual: TextView = view.findViewById(R.id.calendar_actual)
        val forecast: TextView = view.findViewById(R.id.calendar_forecast)
        val previous: TextView = view.findViewById(R.id.calendar_previous)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val event = getItem(position)
        holder.time.text = event.formattedDateTime
        holder.eventName.text = event.eventName
        holder.currency.text = event.currency
        holder.actual.text = event.actual
        holder.forecast.text = event.forecast
        holder.previous.text = event.previous

        val impactColor = when (event.impactLevel) {
            "high" -> android.graphics.Color.parseColor("#FF9500")
            "medium" -> android.graphics.Color.parseColor("#FFCC00")
            else -> android.graphics.Color.parseColor("#34C759")
        }
        holder.impact.setTextColor(impactColor)
        holder.impact.text = event.impactLevel.uppercase()
    }

    class DiffCallback : DiffUtil.ItemCallback<EconomicCalendar>() {
        override fun areItemsTheSame(old: EconomicCalendar, new: EconomicCalendar): Boolean {
            return old.date == new.date && old.eventName == new.eventName
        }

        override fun areContentsTheSame(old: EconomicCalendar, new: EconomicCalendar): Boolean {
            return old == new
        }
    }
}