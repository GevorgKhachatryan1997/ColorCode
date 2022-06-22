package com.example.colorcode

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var colorTextView: TextView = itemView.findViewById(R.id.tvColor)

    fun bind(colorCode: String) {
        colorTextView.apply {
            this.text = colorCode
            this.setBackgroundColor(Color.parseColor(colorCode))
        }
    }
}