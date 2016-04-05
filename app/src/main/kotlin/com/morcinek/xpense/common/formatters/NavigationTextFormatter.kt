package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.data.project.Project

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NavigationTextFormatter {

    fun formatTitle(project: Project) = if (project.location.isBlank()) project.name else "${project.name}, ${project.location}"

    fun formatAmount(value: Double, currencySymbol: String) = "${CurrencyFormatter().format(value, currencySymbol)}"
}