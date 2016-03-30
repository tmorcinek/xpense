package com.morcinek.xpense.project.currency

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.common.utils.hideSoftInput
import com.morcinek.xpense.project.currency.CurrencyAdapter
import kotlinx.android.synthetic.main.text_picker.*
import org.jetbrains.anko.textChangedListener
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyPickerDialogFragment : TextPickerDialogFragment<Currency>() {

    override val adapter: AbstractRecyclerViewAdapter<Currency, out RecyclerView.ViewHolder> by lazy {
        CurrencyAdapter(context)
    }

    override fun extractString(item: Currency) = "${item.displayName} ${item.currencyCode}"

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.title_currency)
        setupItems()
        setupButton()
        setupEditText()
    }

    private fun setupItems() {
        items = Currency.getAvailableCurrencies().toList()
        adapter.setList(items)
    }

    private fun setupButton() {
        button.isEnabled = false
        button.setImageResource(R.drawable.ic_action_accept)
        button.setOnClickListener({
            setupCurrencySymbol(editText.getTrimString())
        })
    }

    private fun setupEditText() {
        editText.textChangedListener {
            onTextChanged { charSequence, start, before, count ->
                val text = charSequence.toString()
                button.isEnabled = text.isNotBlank() && text.length <= 3
            }
        }
    }

    override fun onItemClicked(item: Currency) {
        setupCurrencySymbol(item.symbol)
    }

    private fun setupCurrencySymbol(symbol: String) {
        onItemSetListener?.invoke(symbol)
        activity.hideSoftInput(editText)
        dialog.dismiss()
    }
}