package com.example.englishdiary
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalenderViewHolder(itemView: View, private val onItemListener: CalenderAdapter.OnItemListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(adapterPosition, dayOfMonth.text.toString())
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}
