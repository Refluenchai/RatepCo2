package com.example.ratepco2.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.ratepco2.R
import com.example.ratepco2.presentation.viewmodel.HouseViewModel

class ETWatcher(
    private val editText: EditText,
    private val viewModel: ViewModel?,
    private val etWatcherCallback: (ViewModel?) -> Unit
) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(e: Editable) {
        if (editText.text.toString() != "" && editText.text.toString() != "0")
            etWatcherCallback(viewModel)
    }
}