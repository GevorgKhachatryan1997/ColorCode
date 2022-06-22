package com.example.colorcode

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class ColorDialog : DialogFragment(R.layout.color_dialog) {

    private var etColorCode: EditText? = null
    private var btnEnterCode: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnterCode = view.findViewById(R.id.btnEntreColorCode)
        etColorCode = view.findViewById(R.id.etColor)

        btnEnterCode?.setOnClickListener {
            val etText = "#${etColorCode?.text.toString()}"
            if (isValid(etText)) {
                (requireActivity() as SelectedColorListener).setColorData(etText)
            } else {
                etColorCode?.error = "code is invalid"
            }
        }
    }

    private fun isValid(colorCode: String): Boolean {
        return try {
            Color.parseColor(colorCode)
            true
        } catch (i: IllegalArgumentException) {
            false
        }
    }

    companion object {
        val COLOR_TAG = ColorDialog::class.simpleName
    }
}

