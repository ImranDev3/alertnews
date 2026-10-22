package com.alertnews.app.adapters

import androidx.recyclerview.widget DiffUtil

class DiffCallback

object NewsItemDiff : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(old: NewsItem, new: NewsItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: NewsItem, new: NewsItem): Boolean {
        return old == new
    }
}

object EconomicCalendarDiff : DiffUtil.ItemCallback<EconomicCalendar>() {
    override fun areItemsTheSame(old: EconomicCalendar, new: EconomicCalendar): Boolean {
        return old.date == new.date && old.eventName == new.eventName
    }

    override fun areContentsTheSame(old: EconomicCalendar, new: EconomicCalendar): Boolean {
        return old == new
    }
}

object IpoDiff : DiffUtil.ItemCallback<IpoEvent>() {
    override fun areItemsTheSame(old: IpoEvent, new: IpoEvent): Boolean {
        return old.companyName == new.companyName
    }

    override fun areContentsTheSame(old: IpoEvent, new: IpoEvent): Boolean {
        return old == new
    }
}