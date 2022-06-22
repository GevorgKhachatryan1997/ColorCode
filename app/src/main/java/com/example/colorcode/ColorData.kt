package com.example.colorcode

object ColorData {
    private var data = mutableListOf<String>()

    var dataChangeListener: DataChangeListener? = null

    fun getData(): List<String> = data

    fun add(color: String) {
        data.add(color)
        dataChangeListener?.onDataChange(data)
    }

    fun clearListener() {
        dataChangeListener = null
    }

    fun interface DataChangeListener {
        fun onDataChange(data: List<String>)
    }
}