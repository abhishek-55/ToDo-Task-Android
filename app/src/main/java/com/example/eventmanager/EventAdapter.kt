package com.example.eventmanager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.solver.state.State
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class EventAdapter(private val context: Context, private val listener: IEventAdapter) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    val allEvents = ArrayList<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
       val viewHolder = EventViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            Log.d("Delete", "delete button clicked")
            listener.onItemClick(allEvents[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allEvents.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = allEvents[position]
        holder.TitleItemView.text = current.header
        holder.TextItemView.text = current.text
    }
    fun updateList(newList: List<Event>){
         allEvents.clear()
        allEvents.addAll(newList)
        notifyDataSetChanged()
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TitleItemView: TextView = itemView.findViewById(R.id.tvTitle)
        val TextItemView: TextView = itemView.findViewById(R.id.tvText)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_btn)
    }
}
interface IEventAdapter{
    fun onItemClick(event: Event)
}