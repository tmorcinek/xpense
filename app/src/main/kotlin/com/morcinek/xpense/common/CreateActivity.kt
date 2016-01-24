package com.morcinek.xpense.common

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.finishOk
import com.morcinek.xpense.common.utils.putParcelable
import kotlinx.android.synthetic.main.expense.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class CreateActivity<T : Parcelable> : AppCompatActivity() {

    protected abstract var item: T

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupToolbar()
        restoreIntentItem()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable(item)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        item = restoreItem(savedInstanceState!!)
    }

    abstract fun restoreItem(bundle: Bundle): T

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_cancel)
    }

    private fun restoreIntentItem() {
        item = restoreItem(intent.extras)
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
                onDoneItemSelected()
                finishOk()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    abstract protected fun onDoneItemSelected()
}