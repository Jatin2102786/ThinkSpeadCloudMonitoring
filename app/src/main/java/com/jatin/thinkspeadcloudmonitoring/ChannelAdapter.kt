package com.jatin.thinkspeadcloudmonitoring
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ChannelAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Channel, ChannelAdapter.ViewHolder>(DiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channel_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val channel = getItem(position)
        holder.bind(channel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.channelTitle) // Update IDs as per your layout

        private val channelId : TextView = itemView.findViewById(R.id.channelId)
        fun bind(channel: Channel) {
            nameTV.text = channel.name
            channelId.text = channel.id

            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(adapterPosition)
                true
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Channel, newItem: Channel) = oldItem == newItem
    }
}