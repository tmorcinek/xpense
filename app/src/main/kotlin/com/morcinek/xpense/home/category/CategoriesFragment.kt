package com.morcinek.xpense.home.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.recyclerview.DividerItemDecoration
import com.morcinek.xpense.common.utils.getParcelableExtra
import com.morcinek.xpense.common.utils.getSerializableExtra
import com.morcinek.xpense.common.utils.setTitle
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.data.CollectionAction
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.expense.ExpenseManager
import com.morcinek.xpense.expense.category.CategoriesPickerDialogAdapter
import com.morcinek.xpense.home.category.create.CreateCategoryActivity
import kotlinx.android.synthetic.main.overview.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CategoriesFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutResourceId() = R.layout.default_list

    @Inject
    lateinit var categoryManager: CategoryManager

    @Inject
    lateinit var expenseManager: ExpenseManager

    private val adapter: CategoriesAdapter
        get() = recyclerView.adapter as CategoriesAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.application as Application).component.inject(this)

        registerFAB(this)
        setTitle(R.string.categories_label)
        setupRecyclerView()
        setupAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val action = data!!.getSerializableExtra<CollectionAction>()!!
            val category = data.getParcelableExtra<Category>()
            adapter.updateList(categoryManager.getCategories(), category, action)
            expenseManager.updateExpenses()
        }
    }

    override fun onClick(v: View?) {
        activity.startActivityFromFragment<CreateCategoryActivity>(this)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
        recyclerView.addItemDecoration(DividerItemDecoration(context, showFirst = true, showLast = true))
        recyclerView.adapter = CategoriesAdapter(context)
        adapter.setItemClickListener {
            activity.startActivityFromFragment<CreateCategoryActivity>(this, it)
        }
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left)

    private fun setupAdapter() {
        adapter.setList(categoryManager.getCategories())
        recyclerView.startLayoutAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterFAB()
    }
}