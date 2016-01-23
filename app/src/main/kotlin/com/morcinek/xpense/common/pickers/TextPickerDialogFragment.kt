package com.morcinek.xpense.common.pickers

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter.OnItemClickListener
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import kotlinx.android.synthetic.main.text_picker.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class TextPickerDialogFragment<T : Any> : DialogFragment(), OnItemClickListener<T>, TextWatcher {

    lateinit var onItemSetListener: (T) -> Unit

    lateinit var adapter: AbstractRecyclerViewAdapter<out Any, out RecyclerView.ViewHolder>

    lateinit var items: List<T>

    var selectedItem: T? = null

    lateinit var isButtonVisible: (String) -> Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.text_picker, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        setupEditText()
    }

    protected fun setTitle(title: Int) {
        titleView.setText(title)
    }

    private fun setupAdapter() {
        adapter.itemClickListener = this
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, R.drawable.string_divider, showLast = true))
    }

    private fun setupEditText() {
        editText.addTextChangedListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        editText.removeTextChangedListener(this)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        adapter.setList(items.filter { it.toString().startsWith(s!!, false) })
        button.isEnabled = isButtonVisible.invoke(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}
