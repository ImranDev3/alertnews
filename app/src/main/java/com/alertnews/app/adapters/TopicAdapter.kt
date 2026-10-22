package com.alertnews.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.alertnews.app.R
import com.alertnews.app.data.model.Topic

class TopicAdapter(
    private val topics: List<Topic>,
    private val onTopicSelected: (Topic, Boolean) -> Unit
) : ListAdapter<Topic, TopicAdapter.TopicViewHolder>(DiffCallback()) {

    private var selectedTopics: Set<String> = emptySet()

    fun setSelectedTopics(selected: Set<String>) {
        selectedTopics = selected
        notifyDataSetChanged()
    }

    class TopicViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.topic_name)
        val container: ConstraintLayout = view.findViewById(R.id.topic_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        holder.name.text = topic.name

        val isSelected = topic.name in selectedTopics
        holder.container.alpha = if (isSelected) 1.0f else 0.6f
        holder.container.setOnClickListener {
            val newSelection = !isSelected
            if (newSelection) {
                holder.container.alpha = 1.0f
            } else {
                holder.container.alpha = 0.6f
            }
            onTopicSelected(topic, newSelection)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(old: Topic, new: Topic): Boolean {
            return old.name == new.name
        }

        override fun areContentsTheSame(old: Topic, new: Topic): Boolean {
            return old == new
        }
    }
}