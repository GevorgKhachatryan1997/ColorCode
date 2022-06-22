package com.example.colorcode

import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorcode.ColorDialog.Companion.COLOR_TAG
import com.google.android.material.floatingactionbutton.FloatingActionButton
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MainActivity : AppCompatActivity(), SelectedColorListener {

    private var recyclerView: RecyclerView? = null
    private var btnAddColor: FloatingActionButton? = null
    private val colorAdapter = ColorAdapter()

    private val dataChangeListener = ColorData.DataChangeListener {
        colorAdapter.updateData(ColorData.getData())
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
        swipeItemRecycleView()
        ColorData.dataChangeListener = dataChangeListener
    }

    override fun setColorData(data: String) {
        ColorData.add(data)
    }

    private fun initRecycleView() {
        recyclerView?.adapter = colorAdapter
        colorAdapter.updateData(ColorData.getData())
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    private fun swipeItemRecycleView() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onChildDraw(
                    c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                        .addActionIcon(R.drawable.ic_add)
                        .create()
                        .decorate()
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    // Take action for the swiped item
                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }



    override fun onDestroy() {
        ColorData.clearListener()
        super.onDestroy()
    }

}