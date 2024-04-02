package com.jatin.thinkspeadcloudmonitoring

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChannelAdapter (private val context: Context,private val itemList: ArrayList<Channel>,
                      val itemClick: RecyclerViewActivity) :
    RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView = itemView.findViewById(R.id.channelTitle)
        var id: TextView = itemView.findViewById(R.id.channelId)
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClick.onItemClick(position)
            }
        }

        init {
            itemView.setOnClickListener {
                itemClick.onItemClick(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channel_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList[position].name
        holder.id.text = itemList[position].id
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}