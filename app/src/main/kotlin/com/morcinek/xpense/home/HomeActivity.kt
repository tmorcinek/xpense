package com.morcinek.xpense.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.startActivity
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.home.config.PreferencesActivity
import com.morcinek.xpense.home.config.fragments.AboutFragment
import com.morcinek.xpense.home.config.fragments.SettingsFragment
import com.morcinek.xpense.home.history.HistoryHostFragment
import com.morcinek.xpense.home.navigation.NavigationExpenseManager
import com.morcinek.xpense.home.overview.OverviewFragment
import com.morcinek.xpense.project.ProjectActivity
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import javax.inject.Inject

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigationExpenseManager: NavigationExpenseManager

    val homeContentController: HomeContentController = HomeContentController(this)

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
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupActionButton() {
        fab.setOnClickListener {
            startActivityFromFragment<ExpenseActivity>(homeContentController.currentFragment)
        }
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
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.overview -> homeContentController.switchFragment(OverviewFragment())
            R.id.history -> homeContentController.switchFragment(HistoryHostFragment())
            R.id.categories -> startActivity<ProjectActivity>()
            R.id.settings -> startActivity<PreferencesActivity>(SettingsFragment::class.java)
            R.id.about -> startActivity<PreferencesActivity>(AboutFragment::class.java)
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
