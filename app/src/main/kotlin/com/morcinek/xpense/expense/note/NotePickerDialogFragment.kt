package com.morcinek.xpense.expense.note

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.setTextWithSelection
import com.morcinek.xpense.common.utils.showSoftInput
import com.morcinek.xpense.data.note.NoteManager
import kotlinx.android.synthetic.main.text_picker.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NotePickerDialogFragment : TextPickerDialogFragment<String>() {

    @Inject
    lateinit var noteManager: NoteManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        setTitle(R.string.title_note)
        setupItems()
        setupButton()
        setupEditText()
    }

    private fun setupItems() {
        items = noteManager.getNotes()
        adapter.setList(items)
    }

    private fun setupButton() {
        isButtonVisible = { it.isNotBlank() }
        button.setImageResource(R.drawable.ic_action_accept)
        button.setOnClickListener({
            val text = editText.text.toString().trim()
            onItemSetListener.invoke(text)
            noteManager.addNote(text)
            dialog.dismiss()
        })
    }

    private fun setupEditText() {
        dialog.window.showSoftInput()
        editText.setTextWithSelection(selectedItem!!)
    }

    override fun onItemClicked(item: String) {
        editText.setTextWithSelection(item.toString())
    }
}
