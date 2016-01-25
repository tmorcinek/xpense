package com.morcinek.xpense.project

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getParcelable
import com.morcinek.xpense.common.utils.getTrimString
import com.morcinek.xpense.create.CreateActivity
import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import kotlinx.android.synthetic.main.project.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.__TextWatcher
import org.jetbrains.anko.textChangedListener
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ProjectActivity : CreateActivity<Project>() {

    @Inject
    lateinit var projectManager: ProjectManager

    override var item: Project
        get() = Project(nameEditText.getTrimString(), "", currencyEditText.getTrimString())
        set(value) {
            nameEditText.setText(value.name)
            //            locationEditText.setText(value.location)
            currencyEditText.setText(value.currency)
        }

    override val validator: Validator<Project> by lazy { ProjectValidator(projectManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project)
        (application as Application).component.inject(this)

        setupProject()
        setupEditTextViews()
    }

    private fun setupProject() {
        item = Project("", "")
    }

    private fun setupEditTextViews() {
        val init: __TextWatcher.() -> Unit = {
            onTextChanged { charSequence, start, before, count ->
                invalidateItem()
            }
        }
        nameEditText.textChangedListener(init)
        currencyEditText.textChangedListener(init)
        locationButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(autocompleteFilter()).build(this@ProjectActivity)
                    startActivityForResult(intent, 0)
                } catch (e: Exception) {
                    Snackbar.make(view, R.string.google_play_services_error, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun autocompleteFilter() = AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val place = PlaceAutocomplete.getPlace(this, data);
        }
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Project>()

    override fun onDoneItemSelected() {
        projectManager.addProject(item)
    }
}