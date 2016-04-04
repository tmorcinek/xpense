package com.morcinek.xpense.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.helpers.ExitHelper
import com.morcinek.xpense.common.utils.startActivity
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.home.category.CategoriesFragment
import com.morcinek.xpense.home.config.PreferencesActivity
import com.morcinek.xpense.home.config.fragments.AboutFragment
import com.morcinek.xpense.home.config.fragments.SettingsFragment
import com.morcinek.xpense.home.history.HistoryFragment
import com.morcinek.xpense.home.navigation.NavigationExpenseManager
import com.morcinek.xpense.home.overview.OverviewFragment
import com.morcinek.xpense.home.statistics.StatsFragment
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import javax.inject.Inject

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigationExpenseManager: NavigationExpenseManager

    private val homeContentController: HomeContentController = HomeContentController(this)

    private val exitHelper = ExitHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        (application as Application).component.inject(this)

        setupToolbar()
        setupToggle()
        setupActionButton()
        setupNavigationView()
        setupFragment(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateNavigationView()
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            setupInitialFragment()
        }
    }

    private fun setupInitialFragment() {
        homeContentController.switchFragment(OverviewFragment())
        navigationView.setCheckedItem(R.id.overview)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupToggle() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.empty, R.string.empty)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupActionButton() {
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        startActivityFromFragment<ExpenseActivity>(homeContentController.currentFragment)
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
        updateNavigationView()
    }

    private fun updateNavigationView() {
        val headerView = navigationView.getHeaderView(0)
        headerView.title.text = navigationExpenseManager.title
        headerView.subtitle.text = navigationExpenseManager.subtitle
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (exitHelper.canExit()) {
                super.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        exitHelper.reset()
        when (item.itemId) {
            R.id.overview -> homeContentController.switchFragment(OverviewFragment())
            R.id.history -> homeContentController.switchFragment(HistoryFragment())
            R.id.statistics -> homeContentController.switchFragment(StatsFragment())
            R.id.categories -> homeContentController.switchFragment(CategoriesFragment())
            R.id.settings -> startActivity<PreferencesActivity>(SettingsFragment::class.java)
            R.id.about -> startActivity<PreferencesActivity>(AboutFragment::class.java)
        }
        when (item.itemId) {
            R.id.overview, R.id.history, R.id.statistics, R.id.categories -> drawer.closeDrawer(GravityCompat.START)
        }
        return true
    }
}
