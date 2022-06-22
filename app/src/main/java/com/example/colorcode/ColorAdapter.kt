package com.example.colorcode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter : RecyclerView.Adapter<ColorViewHolder>() {

    private val colors = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount() = colors.size

    fun updateData(newColorsList: List<String>) {
        colors.apply {
            clear()
            addAll(newColorsList)
        }
        notifyDataSetChanged()
    }
}