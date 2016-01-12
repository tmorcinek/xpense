package com.morcinek.xpense.expense

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.adapter.AbstractRecyclerViewAdapter
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import kotlinx.android.synthetic.main.expense.*
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExpenseActivity : AppCompatActivity(), AbstractRecyclerViewAdapter.OnItemClickListener<Int> {
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)
        (application as Application).component.inject(this)

        setSupportActionBar(toolbar)
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        expenseAdapter = ExpenseAdapter(this)
        expenseAdapter.setItemClickListener(this)
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
            R.id.action_done -> {
                setResult(RESULT_OK)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: Int) {
        when (item) {
            R.string.title_amount -> TODO() //start amount activity
            R.string.title_category -> TODO() // start category picker activity
            R.string.title_note -> TODO() // start text activity
            R.string.title_date -> startDatePicker(expenseAdapter.expense.date)
        }
    }

    private fun startDatePicker(calendar: Calendar) {
        val calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment.newInstance(
                { dialogFragment, year, month, day ->
                    calendar.set(year, month, day)
                    expenseAdapter.notifyDataSetChanged()
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendarDatePickerDialogFragment.setThemeCustom(R.style.BetterPickersTheme);
        calendarDatePickerDialogFragment.show(supportFragmentManager, "");
    }
}
