package com.example.colorcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorcode.ColorDialog.Companion.COLOR_TAG
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), SelectedColorListener {

    private var recyclerView: RecyclerView? = null
    private var btnAddColor: FloatingActionButton? = null
    private val colorAdapter = ColorAdapter()

    private val dataChangeListener = ColorData.DataChangeListener {
        colorAdapter.updateData(ColorData.getData())
    }

    private val itemTouchHelper = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            ColorData.remove(viewHolder.adapterPosition)
            ColorData.dataChangeListener = dataChangeListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.colorRecyclerView)
        btnAddColor = findViewById(R.id.btnAddColor)
        btnAddColor?.setOnClickListener {
            ColorDialog().show(supportFragmentManager, COLOR_TAG)
        }

        initRecycleView()
        ColorData.dataChangeListener = dataChangeListener
    }

    override fun setColorData(data: String) {
        ColorData.add(data)
    }

    private fun initRecycleView() {
        recyclerView?.adapter = colorAdapter
        colorAdapter.updateData(ColorData.getData())
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        ColorData.clearListener()
        super.onDestroy()
    }
}