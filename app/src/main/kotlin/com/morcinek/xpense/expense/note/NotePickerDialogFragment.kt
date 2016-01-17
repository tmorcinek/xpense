package com.morcinek.xpense.expense.note

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import kotlinx.android.synthetic.main.text_picker.*
import org.jetbrains.anko.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NotePickerDialogFragment : TextPickerDialogFragment<String>() {

    override fun getLayoutId() = R.layout.text_picker

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        setEditText(selectedItem)
    }

    private fun setEditText(text: String) {
        editText.setText(text)
        editText.setSelection(text.length)
    }

    override fun onItemClicked(item: String) {
        setEditText(item.toString())
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onTextSetListener(this, editText.text.toString())
            dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            dialog.dismiss()
            return true
        }
        return false
    }
}
