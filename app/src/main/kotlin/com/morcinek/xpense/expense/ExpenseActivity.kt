package com.morcinek.xpense.expense

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment
import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.pickers.TextPickerDialogFragment
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.betterpickers.setCurrentNumberAsInteger
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.common.utils.putParcelable
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.expense.category.CategoryAdapter
import com.morcinek.xpense.expense.category.CategoryPickerDialogFragment
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.data.expense.Expense
import com.morcinek.xpense.expense.note.NoteAdapter
import com.morcinek.xpense.expense.note.NotePickerDialogFragment
import com.morcinek.xpense.data.note.NoteManager
import kotlinx.android.synthetic.main.expense.*
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseActivity : AppCompatActivity(), AbstractRecyclerViewAdapter.OnItemClickListener<Int> {

    @Inject
    lateinit var noteManager: NoteManager

    @Inject
    lateinit var categoryManager: CategoryManager

    @Inject
    lateinit var expenseManager: ExpenseManager

    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)
        (application as Application).component.inject(this)

        setupToolbar()
        setupAdapter()
        setupRecyclerView()
        setupExpense()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        expenseAdapter.expense = savedInstanceState!!.getParcelable()!!
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable(expenseAdapter.expense)
    }

    private fun setupExpense() {
        val expense = intent.getParcelableExtra<Expense>("expense")
        if (expense != null) {
            setTitle(R.string.edit_expense_label)
            expenseAdapter.expense = expense
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_cancel)
    }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.expense, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_done -> {
                expenseManager.addExpense(expenseAdapter.expense)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(item: Int) {
        when (item) {
            R.string.title_amount -> startAmountPicker(expenseAdapter.expense)
            R.string.title_category -> startCategoryPicker(expenseAdapter.expense)
            R.string.title_note -> startTextPicker(expenseAdapter.expense)
            R.string.title_date -> startDatePicker(expenseAdapter.expense)
        }
    }

    private fun startAmountPicker(expense: Expense) {
        val numberPickerBuilder = NumberPickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setPlusMinusVisibility(View.GONE)
                .setLabelText("$")
                .addNumberPickerDialogHandler { reference, number, decimal, isNegative, fullNumber ->
                    expense.value = fullNumber
                    expenseAdapter.notifyDataSetChanged()
                }
        numberPickerBuilder.setCurrentNumberAsInteger(expense.value)
        numberPickerBuilder.show()
    }

    private fun startCategoryPicker(expense: Expense) {
        val textPickerFragment = CategoryPickerDialogFragment()
        textPickerFragment.adapter = CategoryAdapter(this)
        textPickerFragment.items = categoryManager.getCategories()
        textPickerFragment.selectedItem = expense.category
        textPickerFragment.onItemSetListener = { textPickerFragment, item ->
            expense.category = item
            expenseAdapter.notifyDataSetChanged()
        }
        textPickerFragment.show(supportFragmentManager, TextPickerDialogFragment::class.java.name)
    }

    private fun startTextPicker(expense: Expense) {
        val textPickerFragment = NotePickerDialogFragment()
        textPickerFragment.adapter = NoteAdapter(this)
        textPickerFragment.items = noteManager.getNotes()
        textPickerFragment.selectedItem = expense.note
        textPickerFragment.onItemSetListener = { textPickerFragment, text ->
            expense.note = text
            expenseAdapter.notifyDataSetChanged()
        }
        textPickerFragment.show(supportFragmentManager, TextPickerDialogFragment::class.java.name)
    }

    private fun startDatePicker(expense: Expense) {
        val calendar = expense.date
        val calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment.newInstance(
                { dialogFragment, year, month, day ->
                    calendar.set(year, month, day)
                    expenseAdapter.notifyDataSetChanged()
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        calendarDatePickerDialogFragment.setThemeCustom(R.style.BetterPickersTheme)
        calendarDatePickerDialogFragment.show(supportFragmentManager, CalendarDatePickerDialogFragment::class.java.name)
    }
}
