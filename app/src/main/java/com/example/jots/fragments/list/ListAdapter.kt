package com.example.jots.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jots.R
import com.example.jots.model.LogEntry
import java.util.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var logEntryList = emptyList<LogEntry>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
        }

        override fun getItemCount(): Int {
            return logEntryList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentItem = logEntryList[position]
            holder.itemView.findViewById<TextView>(R.id.textViewTimestamp).text = Date(currentItem.le_timestamp).toString()
            holder.itemView.findViewById<TextView>(R.id.textViewContent).text = currentItem.content.toString()

            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
               val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }

        fun setData(entry: List<LogEntry>) {
           this.logEntryList = entry
            notifyDataSetChanged()
        }
    }