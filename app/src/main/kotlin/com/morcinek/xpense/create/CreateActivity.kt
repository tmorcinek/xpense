package com.morcinek.xpense.create

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.finishOk
import com.morcinek.xpense.common.utils.putParcelable
import com.morcinek.xpense.common.utils.putParcelableExtra
import com.morcinek.xpense.common.utils.putSerializableExtra
import com.morcinek.xpense.data.CollectionAction
import kotlinx.android.synthetic.main.expense.*
import org.jetbrains.anko.alert

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class CreateActivity<T : Parcelable> : AppCompatActivity() {

    protected abstract var item: T

    protected abstract val validator: Validator<T>

    protected val isEditMode by lazy { intent.extras != null }

    protected val editItem: T? by lazy { if (isEditMode) restoreItem(intent.extras)!! else null }

    protected open val canDelete: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.partial_fade_out)
    }

    private fun successFinish() {
        finishOk()
        overridePendingTransition(R.anim.partial_fade_in, R.anim.slide_out_bottom)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupToolbar()
        restoreIntentItem()
        setupTitle()
    }

    private fun setupTitle() {
        title = getString(activityLabel(), item.javaClass.simpleName)
    }

    private fun activityLabel() = if (isEditMode) R.string.activity_label_edit else R.string.activity_label_new

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable(item)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        item = restoreItem(savedInstanceState!!)!!
    }

    abstract fun restoreItem(bundle: Bundle): T?

    protected fun invalidateItem() {
        supportInvalidateOptionsMenu()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_action_cancel)
    }

    private fun restoreIntentItem() {
        if (isEditMode) {
            item = restoreItem(intent.extras)!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.action_done).setEnabled(validator.isValid(item))
        menu.findItem(R.id.action_delete).setVisible(isEditMode && canDelete)
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
                successFinish()
                return true
            }
            R.id.action_delete -> {
                showConfirmationDialog()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmationDialog() {
        alert(R.string.item_delete_message) {
            positiveButton(R.string.yes) {
                onDeleteItemSelected()
                successFinish()
            }
            negativeButton(R.string.no)
        }.show()
    }

    protected open fun onDoneItemSelected() {
        if (isEditMode) {
            intent.putSerializableExtra(CollectionAction.UPDATED)
        } else {
            intent.putSerializableExtra(CollectionAction.CREATED)
        }
        intent.putParcelableExtra(item)
    }

    protected open fun onDeleteItemSelected() {
        intent.putSerializableExtra(CollectionAction.DELETED)
        intent.putParcelableExtra(item)
    }
}