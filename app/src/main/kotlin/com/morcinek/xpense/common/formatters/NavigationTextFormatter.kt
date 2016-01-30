package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.BuildConfig
import com.morcinek.xpense.common.utils.isInteger
import com.morcinek.xpense.data.project.Project

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NavigationTextFormatter {

    fun formatTitle(project: Project) = if (project.location.isBlank()) project.name else "${project.name}, ${project.location}"

    fun formatSubtitle(text: String, value: Double, currencySymbol: String) = if (value.isInteger()) {
        BuildConfig.NAV_SUBTITLE_FORMAT_NO_DECIMAL.format(text, value, currencySymbol)
    } else {
        BuildConfig.NAV_SUBTITLE_FORMAT.format(text, value, currencySymbol)
    }
}