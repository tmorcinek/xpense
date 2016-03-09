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
import com.morcinek.xpense.common.utils.show
import com.morcinek.xpense.create.CreateActivity
import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager
import kotlinx.android.synthetic.main.button_item.view.*
import kotlinx.android.synthetic.main.project.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textChangedListener
import org.jetbrains.anko.textResource
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ProjectActivity : CreateActivity<Project>(), View.OnClickListener {

    @Inject
    lateinit var projectManager: ProjectManager

    override var item: Project
        get() = Project(nameEditText.getTrimString(), locationButton.subtitle.text.toString(), currencyButton.subtitle.text.toString())
        set(value) {
            nameEditText.setText(value.name)
            locationButton.subtitle.setText(value.location)
            currencyButton.subtitle.setText(value.currency)
        }

    override val validator: Validator<Project> by lazy { ProjectValidator(projectManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project)
        (application as Application).component.inject(this)

        setupProject()
        setupEditText()
        setupButtons()
    }

    private fun setupProject() {
        item = Project(getString(R.string.default_value_empty), getString(R.string.default_value_none), getString(R.string.default_value_currency))
    }

    private fun setupEditText() {
        nameEditText.textChangedListener({
            onTextChanged { charSequence, start, before, count ->
                invalidateItem()
            }
        })
    }

    private fun setupButtons() {
        setupButton(locationButton, R.drawable.ic_pin_black, R.string.project_location_hint)
        setupButton(currencyButton, R.drawable.ic_money_black, R.string.project_currency_hint)
    }

    private fun setupButton(button: View, icon: Int, title: Int) {
        button.icon.imageResource = icon
        button.title.textResource = title
        button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.currencyButton -> handleCurrencyButton()
            R.id.locationButton -> handleLocationButton(view)
        }
        nameEditText.clearFocus()
        view.requestFocus()
    }

    private fun handleCurrencyButton() {
        val textPickerFragment = CurrencyPickerDialogFragment()
        textPickerFragment.onItemSetListener = {
            currencyButton.subtitle.setText(it)
        }
        textPickerFragment.show(supportFragmentManager)
    }


    private fun handleLocationButton(view: View) {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(autocompleteFilter()).build(this)
            startActivityForResult(intent, 0)
        } catch (e: Exception) {
            Snackbar.make(view, R.string.google_play_services_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun autocompleteFilter() = AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val place = PlaceAutocomplete.getPlace(this, data);
            locationButton.subtitle.setText(place.address)
        }
    }

    override fun restoreItem(bundle: Bundle) = bundle.getParcelable<Project>()

    override fun onDoneItemSelected() {
        val project = item
        projectManager.addProject(project)
    }
}