package com.morcinek.xpense.expense

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment
import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.create.CreateActivity
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.betterpickers.setCurrentNumberAsInteger
import com.morcinek.xpense.common.utils.finishOk
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.expense.category.CategoryAdapter
import com.morcinek.xpense.expense.category.CategoryPickerDialogFragment
import com.morcinek.xpense.expense.note.NoteAdapter
import com.morcinek.xpense.expense.note.NotePickerDialogFragment
import kotlinx.android.synthetic.main.expense.*
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseActivity : CreateActivity<Expense>(), AbstractRecyclerViewAdapter.OnItemClickListener<Int> {

    override var item: Expense
        get() = expenseAdapter.expense
        set(value) {
            expenseAdapter.expense = value
        }

    override val validator: Validator<Expense> by lazy { ExpenseValidator() }

    @Inject
    lateinit var expenseManager: ExpenseManager

    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)
        (application as Application).component.inject(this)

        setupAdapter()
        setupRecyclerView()
        setupExpense()
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Expense>()

    private fun setupAdapter() {
        expenseAdapter = ExpenseAdapter(this)
        expenseAdapter.itemClickListener = this
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = expenseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context = this, showLast = true))
    }

    private fun setupExpense() {
        if (intent.extras != null) {
            setTitle(R.string.edit_expense_label)
        }
    }

    override fun onDoneItemSelected() {
        expenseManager.addExpense(expenseAdapter.expense)
    }

    override fun onItemClicked(item: Int) {
        when (item) {
            AMOUNT_ITEM -> startAmountPicker(expenseAdapter.expense)
            CATEGORY_ITEM -> startCategoryPicker(expenseAdapter.expense)
            NOTE_ITEM -> startTextPicker(expenseAdapter.expense)
            DATE_ITEM -> startDatePicker(expenseAdapter.expense)
        }
    }

    private fun startAmountPicker(expense: Expense) {
        val numberPickerBuilder = NumberPickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setPlusMinusVisibility(View.GONE)
                .setLabelText("$")
                .setCurrentNumberAsInteger(expense.value)
                .addNumberPickerDialogHandler { reference, number, decimal, isNegative, fullNumber ->
                    expense.value = fullNumber
                    expenseAdapter.notifyDataItemChanged(AMOUNT_ITEM)
                }
        numberPickerBuilder.show()
    }

    private fun startCategoryPicker(expense: Expense) {
        val textPickerFragment = CategoryPickerDialogFragment()
        textPickerFragment.selectedItem = expense.category
        textPickerFragment.onItemSetListener = {
            expense.category = it
            expenseAdapter.notifyDataItemChanged(CATEGORY_ITEM)
        }
        textPickerFragment.show(supportFragmentManager, TextPickerDialogFragment::class.java.name)
    }

    private fun startTextPicker(expense: Expense) {
        val textPickerFragment = NotePickerDialogFragment()
        textPickerFragment.selectedItem = expense.note
        textPickerFragment.onItemSetListener = {
            expense.note = it
            expenseAdapter.notifyDataItemChanged(NOTE_ITEM)
        }
        textPickerFragment.show(supportFragmentManager, TextPickerDialogFragment::class.java.name)
    }

    private fun startDatePicker(expense: Expense) {
        val calendar = expense.date
        val calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment.newInstance(
                { dialogFragment, year, month, day ->
                    calendar.set(year, month, day)
                    expenseAdapter.notifyDataItemChanged(DATE_ITEM)
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        calendarDatePickerDialogFragment.setThemeCustom(R.style.BetterPickersTheme)
        calendarDatePickerDialogFragment.show(supportFragmentManager, CalendarDatePickerDialogFragment::class.java.name)
    }
}
