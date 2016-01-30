package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.BuildConfig
import com.morcinek.xpense.common.utils.isInteger

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyFormatter {

    fun format(value: Double, currencySymbol: String) = if (value.isInteger()) {
        BuildConfig.CURRENCY_FORMAT_NO_DECIMAL.format(value, currencySymbol)
    } else {
        BuildConfig.CURRENCY_FORMAT.format(value, currencySymbol)
    }
}