package com.morcinek.xpense.create

import android.os.Bundle
import android.os.Parcelable
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

    protected abstract val validator: Validator<T>

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
        item = restoreItem(savedInstanceState!!)!!
    }

    abstract fun restoreItem(bundle: Bundle): T?

    protected fun invalidateItem(){
        supportInvalidateOptionsMenu()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_cancel)
    }

    private fun restoreIntentItem() {
        if (intent.extras != null) {
            item = restoreItem(intent.extras)!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.action_done).setEnabled(validator.isValid(item))
        return super.onPrepareOptionsMenu(menu)
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