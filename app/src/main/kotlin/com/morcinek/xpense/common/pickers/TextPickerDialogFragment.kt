package com.morcinek.xpense.common.pickers

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter.OnItemClickListener
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import kotlinx.android.synthetic.main.text_picker.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class TextPickerDialogFragment : DialogFragment(), OnItemClickListener<Any>, TextView.OnEditorActionListener, TextWatcher {

    lateinit var onTextSetListener: (TextPickerDialogFragment, String) -> Unit

    lateinit var adapter: AbstractRecyclerViewAdapter<out Any, out RecyclerView.ViewHolder>

    lateinit var items: List<Any>

    lateinit var text: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.text_picker, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        setupAdapter()
        setupRecyclerView()
        setupEditText()
    }

    private fun setupAdapter() {
        adapter.setList(items)
        adapter.setItemClickListener(this)
    }

    private fun setupRecyclerView() {
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.addItemDecoration(DividerItemDecoration(context, R.drawable.string_divider, showLast = true))
        recyclerView.setAdapter(adapter)
    }

    private fun setupEditText() {
        editText.addTextChangedListener(this)
        editText.setOnEditorActionListener(this)
        setEditText(text)
    }

    private fun setEditText(text: String) {
        editText.setText(text)
        editText.setSelection(text.length)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        editText.removeTextChangedListener(this)
    }

    override fun onItemClicked(item: Any) {
        setEditText(item.toString())
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onTextSetListener(this, editText.text.toString())
            dialog.dismiss()
            return true
        }
        return false
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        adapter.setList(items.filter { it.toString().startsWith(s!!, false) })
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}
